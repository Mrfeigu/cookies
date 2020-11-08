package com.delicacy.cookies.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * todo 自闭锁，淦，要修改
 *
 * 自定义锁，不可重入
 *
 *
 * 参考：
 * unsafe使用：https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
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
    private volatile int state = 0;

    /**
     * 持有当前线程
     */
    private Thread currentThreadHolder;

    /**
     * 等待队列
     */
    private ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

    /**
     * Unsafe类为一单例实现，提供静态方法getUnsafe获取Unsafe实例，
     * 当且仅当调用getUnsafe方法的类为引导类加载器所加载时才合法，
     * 否则抛出SecurityException异常。
     */
    private static final Unsafe unsafe;

    static {
        // 通过反射获取到unsafe类
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception ex){
            throw new RuntimeException("myLock init error");
        }
    }

    /**
     * cas原子比较
      * @return
     * @throws NoSuchFieldException
     */
    private boolean stateCas(int origin, int expect) throws NoSuchFieldException {
        Field field = this.getClass().getDeclaredField("state");
        long offset = unsafe.objectFieldOffset(field);
        return unsafe.compareAndSwapInt(MyLock.class, offset, origin, expect);
    }

    /**
     * 锁获取， 队列没有线程 或者 队列首个线程是当前线程
     * 一开始就保证入队跟出队操作
     *
     * @return
     * @throws NoSuchFieldException
     */
    private boolean acquire() throws NoSuchFieldException {
        Thread currentThread = Thread.currentThread();
        queue.add(currentThread);
        if(queue.isEmpty() || queue.peek().equals(currentThread)){
            if(state == UN_LOCK_STATE && stateCas(UN_LOCK_STATE, LOCK_STATE)){
                currentThreadHolder = currentThread;
                queue.poll();
                return true;
            }
        }
        return false;
    }


    /**
     * 上锁
     * @return
     */
    public void lock() throws NoSuchFieldException {
        for(;;){
            if(acquire()){
                return;
            }
            LockSupport.park();
        }
    }

    /**
     * 释放锁
     * @return
     */
    public void unLock() throws NoSuchFieldException {
        Thread currentThread = Thread.currentThread();
        if(!currentThreadHolder.equals(currentThread)){
            throw new RuntimeException("myLock unLock error currentThreadHolder is not current thread!");
        }
        // 修改状态
        if(state == LOCK_STATE && stateCas(LOCK_STATE, UN_LOCK_STATE)){
            currentThreadHolder = null;
            if(!queue.isEmpty()){
                Thread header = queue.peek();
                LockSupport.unpark(header);
            }
        }
    }



}
