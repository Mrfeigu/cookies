package com.delicacy.cookies.redisson.distribute.utils;

import com.delicacy.cookies.redisson.entity.Hero;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.redisson.api.*;
import org.redisson.api.map.event.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
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
public class RedisCollectionMapService implements InitializingBean, DisposableBean {

    @Resource
    private RedissonClient redisson;

    private LocalCachedMapOptions localCachedMapOptions;

    @Resource
    private MapListener mapListener;

    @Override
    public void afterPropertiesSet() throws Exception {
        // Redisson配置map缓存策略
        localCachedMapOptions = LocalCachedMapOptions.defaults()
                // 用于淘汰清除本地缓存内的元素
                // 共有以下几种选择:
                // LFU - 统计元素的使用频率，淘汰用得最少（最不常用）的。
                // LRU - 按元素使用时间排序比较，淘汰最早（最久远）的。
                // SOFT - 元素用Java的WeakReference来保存，缓存元素通过GC过程清除。
                // WEAK - 元素用Java的SoftReference来保存, 缓存元素通过GC过程清除。
                // NONE - 永不淘汰清除缓存元素。
                .evictionPolicy(LocalCachedMapOptions.EvictionPolicy.NONE)
                // 如果缓存容量值为0表示不限制本地缓存容量大小
                .cacheSize(1000)
                // 以下选项适用于断线原因造成了未收到本地缓存更新消息的情况。
                // 断线重连的策略有以下几种：
                // CLEAR - 如果断线一段时间以后则在重新建立连接以后清空本地缓存
                // LOAD - 在服务端保存一份10分钟的作废日志
                //        如果10分钟内重新建立连接，则按照作废日志内的记录清空本地缓存的元素
                //        如果断线时间超过了这个时间，则将清空本地缓存中所有的内容
                // NONE - 默认值。断线重连时不做处理。
                .reconnectionStrategy(LocalCachedMapOptions.ReconnectionStrategy.NONE)
                // 以下选项适用于不同本地缓存之间相互保持同步的情况
                // 缓存同步策略有以下几种：
                // INVALIDATE - 默认值。当本地缓存映射的某条元素发生变动时，同时驱逐所有相同本地缓存映射内的该元素
                // UPDATE - 当本地缓存映射的某条元素发生变动时，同时更新所有相同本地缓存映射内的该元素
                // NONE - 不做任何同步处理
                .syncStrategy(LocalCachedMapOptions.SyncStrategy.INVALIDATE)
                // 每个Map本地缓存里元素的有效时间，默认毫秒为单位
                .timeToLive(10, TimeUnit.SECONDS)
                // 每个Map本地缓存里元素的最长闲置时间，默认毫秒为单位
                .maxIdle(10, TimeUnit.SECONDS);
    }

    @Override
    public void destroy() throws Exception {
        // 这个Bean销毁前执行的方法
    }

    /**
     * map 的基本操作
     * @return
     * @throws InterruptedException
     */
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

    /**
     * 元素淘汰（Eviction） 类
     * 带有元素淘汰（Eviction）机制的映射类允许针对一个映射中每个元素单独设定 有效时间 和 最长闲置时间 。
     * 还保留了插入顺序
     * 目前的Redis自身并不支持散列（Hash）当中的元素淘汰，
     * 因此所有过期元素都是通过org.redisson.EvictionScheduler实例来实现定期清理的。
     * 为了保证资源的有效利用，每次运行最多清理300个过期元素。
     *
     *
     *
     *
     * 本地缓存（LocalCache） 类 -- 本地缓存（Local Cache）也叫就近缓存（Near Cache）。
     * 这类映射的使用主要用于在特定的场景下，映射缓存（MapCache）上的高度频繁的读取操作，使网络通信都被视为瓶颈的情况。
     * Redisson与Redis通信的同时，还将部分数据保存在本地内存里。这样的设计的好处是它能将读取速度提高最多 45倍 。
     * 所有同名的本地缓存共用一个订阅发布话题，所有更新和过期消息都将通过该话题共享。
     *
     * @return
     */
    public Object elementEliminate() {

        // RMapCache 带有元素淘汰功能
        RMapCache<String, String> mapCache = redisson.getMapCache("RMapCacheDemo");
        // 有效时间 ttl = 10分钟
        mapCache.put("厄加特", "卡尔马龙", 10L, TimeUnit.SECONDS);
        String str = mapCache.get("厄加特");
        System.out.println(str);
        // 有效时间 ttl = 10分钟, 最长闲置时间 maxIdleTime = 10秒钟
        mapCache.put("卡萨丁", "虚空之眼", 10, TimeUnit.MINUTES, 10, TimeUnit.SECONDS);
        return str;
    }

    /**
     * 测试本地缓存，效率比操作redis高效45倍，讲得很夸张
     * @return
     */
    public Object localCacheMap(){

        RLocalCachedMap<String, Integer> map = redisson.getLocalCachedMap("test", localCachedMapOptions);

        map.put("123", 1);
        map.putIfAbsent("323", 2);
        map.remove("123");

        // 在不需要旧值的情况下可以使用fast为前缀的类似方法
        map.fastPut("a", 1);
        map.fastPutIfAbsent("d", 32);
        map.fastRemove("b");

        RFuture<Integer> putAsyncFuture = map.putAsync("321", 123);
        map.fastPutAsync("321", 1);

        map.fastPutAsync("321", 1);
        map.fastRemoveAsync("321");

        // 主动销毁这个Bean
        map.destroy();

        return "小男孩";
    }


    /**
     * 监听器事例
     * @return
     */
    public Object MapListener(){

        RMapCache<String, Integer> map = redisson.getMapCache("myMap");
        // 或
        RLocalCachedMap myMap = redisson.getLocalCachedMap("myMap", localCachedMapOptions);

        //
        int updateListener = map.addListener((EntryUpdatedListener<Integer, Integer>) event -> {
            event.getKey(); // 字段名
            event.getValue(); // 新值
            event.getOldValue(); // 旧值
            // ...
        });

        var createListener = map.addListener((EntryCreatedListener<Integer, Integer>) event -> {
            event.getKey(); // 字段名
            event.getValue(); // 值
            // ...
        });

        int expireListener = map.addListener((EntryExpiredListener<Integer, Integer>) event -> {
            event.getKey(); // 字段名
            event.getValue(); // 值
            // ...
        });

        int removeListener = map.addListener((EntryRemovedListener<Integer, Integer>) event -> {
            event.getKey(); // 字段名
            event.getValue(); // 值
        });

        map.removeListener(updateListener);
        map.removeListener(createListener);
        map.removeListener(expireListener);
        map.removeListener(removeListener);

        return "这些监听器真的有用吗？";
    }


    public Object testListener(){

        RMapCache<String, Integer> map = redisson.getMapCache("myMap");
        map.addListener(mapListener);
        map.put("wdnmd", 1);
        map.remove("wdnmd");
        map.put("wdnmd", 2);
        return "监听器使用案例";
    }


}
