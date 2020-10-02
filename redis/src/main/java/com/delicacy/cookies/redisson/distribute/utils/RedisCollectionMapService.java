package com.delicacy.cookies.redisson.distribute.utils;

import com.delicacy.cookies.redisson.entity.Hero;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/redisson/redisson/wiki/7.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%9B%86%E5%90%88
 * redisson映射集合Map
 *
 * @author linzhenghui
 * @date 2020/9/29
 */

@Slf4j
@Service
public class RedisCollectionMapService {

    @Resource
    private RedissonClient redisson;

    public Object map() throws InterruptedException {
        Map<String, Object> map = Maps.newHashMap();
        map.put("崔斯特", new Hero(1, "崔斯特", "卡牌大师"));
        redisson.getMap("RedisCollectionMap").putAll(map);
        RMap<String, Object> redissonMap = redisson.getMap("RedisCollectionMap");

        redissonMap.fastPut("张学友", new Hero(3, "张学友", "乌蝇哥"));

        // 异步写
        RFuture<Object> objectRFuture = redissonMap.putAsync("劫", new Hero(2, "劫", "影流之主"));

        return "城里人真会玩";
    }

    /**
     * redissonMap的demo
     * @return
     * @throws InterruptedException
     */
    public Object lockDemo() throws InterruptedException {

        RMap<String, Object> redissonMap = redisson.getMap("RedisCollectionMap");
        String key = "蒸汽朋克";

        redissonMap.put(key, "放过我吧");
        RLock keyLock = redissonMap.getLock(key);

        // 获得锁
        if (keyLock.tryLock(1L, TimeUnit.SECONDS)) {

            try {
                Thread.sleep(3000L);
                Object o = redissonMap.get(key);
                System.out.println(o);
                System.out.println("就这能拿到锁了？？？");
            } finally {
                keyLock.unlock();
            }

        }else{

            System.out.println("没有拿到锁资源，此次请求失败");

        }
        return "我觉得可以";
    }

    /**
     * redissonMap的读写锁
     * @return
     */
    public Object readWitleLock(){
        RMap<String, Object> redissonMap = redisson.getMap("RedisCollectionMap");
        String key = "蒸汽朋克";
        // 还能拿到读写锁
        RReadWriteLock rwLock = redissonMap.getReadWriteLock(key);
        rwLock.readLock().lock(1L, TimeUnit.SECONDS);
        try {
            Object o = redissonMap.get(key);
            System.out.println(o);
            System.out.println("就这能拿到锁了？？？");
        } finally {
            rwLock.readLock().unlock();
        }
        return "有点香";
    }



}
