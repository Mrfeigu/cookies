package com.delicacy.cookies.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 自定义锁，不可重入
 * <p>
 * <p>
 * 初步建设：
 * 1: cas修改变量
 * 2: 循环获取
 * 3: LockSupport挂起
 * <p>
 * <p>
 * 遇到的问题：
 * 1: 锁不会释放，原因是cas时候没有将状态改变
 * 2: 发现大概率的死锁的情况，原因是存在线程存在重复入队列
 * <p>
 * <p>
 * 优化：
 * 1: 方法上加上final保证不被重写， 而且可能够给调用带来内联，提高效率
 * 2：加上tryLock方法，能够保证超时获取锁失败
 * <p>
 * 不足的地方：
 * 1: 里面用到了一个并发队列，其实可以用原生链表去实现，但是要保证并发问题，这一块是个难题
 * 2：锁实现相对比较粗糙，没有相对完善条件信号量机制
 * <p>
 * 参考：
 * final关键字的含义: https://zhuanlan.zhihu.com/p/60889552
 * unsafe使用: https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
 *
 * @author feigu
 * @date 2020/11/07
 */
public class MyLock {

    /**
     * 无锁常量
     */
    private static final int UN_LOCK_STATE = 0;
    /**
     * 锁常量
     */
    private static final int LOCK_STATE = 1;

    /**
     * 锁标识
     * 0 表示无锁
     * 1 表示已锁
     */
    private int state = 0;

    /**
     * state地址偏移量
     */
    private final long state_offset;

    /**
     * 持有当前线程
     */
    private Thread currentThreadHolder;

    /**
     * 等待队列
     */
    private final ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

    /**
     * Unsafe类为一单例实现，提供静态方法getUnsafe获取Unsafe实例，
     * 当且仅当调用getUnsafe方法的类为引导类加载器所加载时才合法，
     * 否则抛出SecurityException异常。
     */
    private final Unsafe unsafe;

    {

        // 通过反射获取到unsafe类
        try {

            // 加载unsafe类
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            // 加载类偏移量
            Field stateField = this.getClass().getDeclaredField("state");
            state_offset = unsafe.objectFieldOffset(stateField);

        } catch (Exception ex) {
            throw new RuntimeException("myLock init error", ex);
        }

    }

    /**
     * cas原子比较
     *
     * @return
     */
    private boolean stateCas(int origin, int expect) {
        return unsafe.compareAndSwapInt(this, state_offset, origin, expect);
    }

    /**
     * 锁获取， 队列没有线程 或者 队列首个线程是当前线程
     * 一开始就保证入队跟出队操作
     *
     * @return
     */
    private boolean acquire() {
        Thread currentThread = Thread.currentThread();
        if (queue.isEmpty() || queue.peek().equals(currentThread)) {
            if (state == UN_LOCK_STATE && stateCas(UN_LOCK_STATE, LOCK_STATE)) {
                // 设置线程持有为当前线程
                currentThreadHolder = currentThread;
                return true;
            }
        }
        return false;
    }


    /**
     * 上锁
     *
     * @return
     */
    public final void lock() {

        Thread currentThread = Thread.currentThread();

        // 预先尝试获取
        if (acquire()) {
            return;
        }

        // 入队，有且仅有保证一次
        queue.add(currentThread);

        for (; ; ) {
            if (acquire()) {
                // 如果队列不为null，移除唤醒节点
                if (!queue.isEmpty() && queue.peek().equals(currentThread)) {
                    queue.poll();
                }
                return;
            }
            LockSupport.park();
        }
    }


    /**
     * 加入尝试时间
     *
     * @param time
     * @param unit
     * @return
     */
    public final boolean tryLock(long time, TimeUnit unit) {

        // 预先尝试获取
        if (acquire()) {
            return true;
        }

        final Thread currentThread = Thread.currentThread();
        final long delayTime = unit.toNanos(time);
        final long deadLine = System.nanoTime() + delayTime;

        // 入队，有且仅有保证一次
        queue.add(currentThread);

        for (; ; ) {

            if (acquire()) {
                // 如果队列不为null，移除唤醒节点
                if (!queue.isEmpty() && queue.peek().equals(currentThread)) {
                    queue.poll();
                }
                return true;
            }

            if (System.nanoTime() > deadLine) {
                queue.remove(currentThread);
                return false;
            }

            LockSupport.parkNanos(delayTime);
        }

    }


    /**
     * 释放锁
     *
     * @return
     */
    public final void unLock() {
        Thread currentThread = Thread.currentThread();
        if (!currentThreadHolder.equals(currentThread)) {
            throw new RuntimeException("myLock unLock error currentThreadHolder is not current thread!");
        }
        // 修改状态
        if (state == LOCK_STATE && stateCas(LOCK_STATE, UN_LOCK_STATE)) {
            currentThreadHolder = null;
            if (!queue.isEmpty()) {
                Thread header = queue.peek();
                LockSupport.unpark(header);
            }
        }
    }


}
