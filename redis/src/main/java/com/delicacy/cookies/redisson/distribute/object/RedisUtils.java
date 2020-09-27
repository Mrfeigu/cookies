package com.delicacy.cookies.redisson.distribute.object;


import com.delicacy.cookies.RedisCommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 基于redisson实现redis基础操作
 * @author feigu
 * @date 2020/09/26
 */
@Slf4j
@Component(value = "redissonRedisUtils")
public class RedisUtils implements RedisCommonUtils {


    @Resource
    private RedissonClient redissonClient;

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    @Override
    public boolean expire(String key, long time) {
        return redissonClient.getBucket(key).expire(time, TimeUnit.SECONDS);
    }

    @Override
    @Deprecated
    public Long getExpire(String key) {
        return null;
    }

    @Override
    public boolean hasKey(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    @Override
    public void del(String... key) {
        redissonClient.getBuckets().delete(key);
    }

    @Override
    public Object get(String key) {
        return redissonClient.getBucket(key).get();
    }

    @Override
    public boolean set(String key, Object value) {
        redissonClient.getBucket(key).set(value);
        return true;
    }

    @Override
    public boolean set(String key, Object value, long time) {
        redissonClient.getBucket(key).set(value, time, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public Long incr(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(delta);
    }

    @Override
    public Long decr(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(-delta);
    }

    @Override
    public Object hget(String key, String item) {
        return redissonClient.getMap(key).get(item);
    }

    @Override
    public Map<Object, Object> hmget(String key) {
        return redissonClient.getMap(key);
    }

    @Override
    public boolean hmset(String key, Map<String, Object> map) {
        redissonClient.getMap(key).putAll(map);
        return true;
    }

    @Override
    @Deprecated
    public boolean hmset(String key, Map<String, Object> map, long time) {
        return false;
    }

    @Override
    public boolean hset(String key, String item, Object value) {
        redissonClient.getMap(key).put(item, value);
        return true;
    }

    @Override
    @Deprecated
    public boolean hset(String key, String item, Object value, long time) {
        return false;
    }

    @Override
    public void hdel(String key, Object... item) {
        redissonClient.getMap(key).fastRemove(item);
    }

    @Override
    public boolean hHasKey(String key, String item) {
        Object o = redissonClient.getMap(key);
        return o == null;
    }

    @Override
    @Deprecated
    public double hincr(String key, String item, double by) {
        return 0;
    }

    @Override
    @Deprecated
    public double hdecr(String key, String item, double by) {
        return 0;
    }

    @Override
    public Set<Object> sGet(String key) {
        return redissonClient.getSet(key);
    }

    @Override
    public boolean sHasKey(String key, Object value) {
        return redissonClient.getSet(key).contains(value);
    }

    @Override
    public long sSet(String key, Object... values) {
        redissonClient.getSet(key).addAll(Arrays.asList(values));
        return 1L;
    }

    @Override
    @Deprecated
    public long sSetAndTime(String key, long time, Object... values) {
        return 0L;
    }

    @Override
    public long sGetSetSize(String key) {
        return redissonClient.getSet(key).size();
    }

    @Override
    public long setRemove(String key, Object... values) {
        redissonClient.getSet(key).removeAll(Arrays.asList(values));
        return 1L;
    }

    @Override
    public List<Object> lGet(String key, long start, long end) {
        return redissonClient.getList(key).range((int)start, (int)end);
    }

    @Override
    public long lGetListSize(String key) {
        return redissonClient.getList(key).size();
    }

    @Override
    public Object lGetIndex(String key, long index) {
        return redissonClient.getList(key).get((int)index);
    }

    @Override
    public boolean lSet(String key, Object value) {
        return redissonClient.getList(key).add(value);
    }

    @Override
    @Deprecated
    public boolean lSet(String key, Object value, long time) {
        return false;
    }

    @Override
    public boolean lSet(String key, List<Object> value) {
        return redissonClient.getList(key).addAll(value);
    }

    @Override
    @Deprecated
    public boolean lSet(String key, List<Object> value, long time) {
        return false;
    }

    @Override
    public boolean lUpdateIndex(String key, long index, Object value) {
        redissonClient.getList(key).set((int)index, value);
        return true;
    }

    @Override
    public long lRemove(String key, long count, Object value) {
        redissonClient.getList(key).remove(value, (int)count);
        return 1L;
    }
}
