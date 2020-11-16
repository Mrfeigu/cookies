package com.delicacy.cookies.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * zookeeper 缓存，分布式锁等高级特性
 * <p>
 * 参考：
 * 分布式锁实现（敖丙）：https://blog.csdn.net/qq_35190492/article/details/105352672
 * Curator封装：https://www.cnblogs.com/qlqwjy/p/10518900.html
 * 一个额外的zk知识：https://laijianfeng.org/2019/01/%E5%88%A9%E7%94%A8Zookeeper%E5%AE%9E%E7%8E%B0-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81/
 *
 * @author linzhenghui
 * @date 2020/11/13
 */
public class ZookeeperAdvancedUtils {

    public static CuratorFramework client;

    /** 排它，不可重入，可能会死锁*/
    public final static InterProcessLock LOCK1;

    /** 排它，可重入，重入几次就要释放几次*/
    public final static InterProcessLock LOCK2;

    /** 排它，可重入，重入几次就要释放几次，读写锁*/
    public final static InterProcessReadWriteLock LOCK3;

    /** 读锁*/
    public final static InterProcessMutex READ_LOCK;

    /** 写锁*/
    public final static InterProcessMutex WRITE_LOCK;

    static {
        // 重试机制
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString("172.16.253.108:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                // 隔离命名
                .namespace("lzh-test")
                .build();
        client.start();

        LOCK1 = new InterProcessSemaphoreMutex(client, "/debug/myLock1");
        LOCK2 = new InterProcessMutex(client,  "/debug/myLock2");
        LOCK3 = new InterProcessReadWriteLock(client, "/debug/myLock3");

        READ_LOCK = LOCK3.readLock();
        WRITE_LOCK = LOCK3.writeLock();
    }

    /**
     * InterProcessSemaphoreMutex  排它，不可重入，可能会死锁
     * @throws Exception
     */
    public static void lock1Debug() throws Exception {
        // 超时实现 lock.acquire(1L, TimeUnit.SECONDS);
        LOCK1.acquire();
        LOCK1.release();
    }

    /**
     * InterProcessMutex 排它，可重入，入几次就要释放几次，说白了就是没用
     * @throws Exception
     */
    public static void lock2Debug() throws Exception {
        LOCK2.acquire();
        LOCK2.release();
    }

    /**
     * 读写锁，读读不冲突，其余冲突
     * @throws Exception
     */
    public static void lock3Debug() throws Exception {
        READ_LOCK.acquire();
        READ_LOCK.release();

        WRITE_LOCK.acquire();
        WRITE_LOCK.release();
    }

    public static void main(String[] args) {

    }


}
