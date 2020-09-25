package com.delicacy.cookies.redisson.lock;

import java.util.concurrent.TimeUnit;

/**
 * 定义Lock的接口类
 * @author feigu
 *
 */
public interface DistributedLocker {

    void lock(String lockKey);

    void unlock(String lockKey);

    void lock(String lockKey, int timeout);
    
    void lock(String lockKey, TimeUnit unit ,int timeout);
}