package com.delicacy.cookies.redisson.distribute.utils;


import org.redisson.api.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/redisson/redisson/wiki/7.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%9B%86%E5%90%88
 *
 * 基于Redis的Redisson的分布式RMultimap Java对象允许Map中的一个字段值包含多个元素。 字段总数受Redis限制，每个Multimap最多允许有4 294 967 295个不同字段。
 *
 * @author feigu
 * @date 2020/9/29
 */
@Service
public class MultiMapDemoService {

    @Resource
    private RedissonClient redisson;

    /**
     * 基于集（Set）的多值映射（Multimap）
     * @return
     */
    public Object baseSetMultiMap(){
        RSetMultimap<String, Integer> map = redisson.getSetMultimap("myMultimap");
        map.put("wdnmd1", 1);
        map.put("wdnmd1", 1);
        map.put("wdnmd1", 2);
        map.put("wdnmd2", 2);
        map.put("wdnmd2", 2);
        map.put("wdnmd3", 3);

        RSet<Integer> wdnmd3 = map.get("wdnmd3");
        List<Integer> newValues = Arrays.asList(1, 2, 3);

        Set<Integer> oldValues = map.replaceValues("wdnmd3", newValues);
        Set<Integer> removedValues = map.removeAll("wdnmd2");

        return "针不戳";
    }

    /**
     * 基于列表（List）的多值映射（Multimap）
     * @return
     */
    public Object baseListMultiMap(){

        RListMultimap<String, Integer> map = redisson.getListMultimap("test1");
        map.put("wdnmd", 1);
        map.put("wdnmd", 2);
        map.put("wdnmd", 3);
        map.put("wdnmd", 1);

        map.put("wdnmd1", 1);
        map.put("wdnmd1", 2);
        map.put("wdnmd1", 3);
        map.put("wdnmd1", 1);

        List<Integer> allValues = map.get("wdnmd");

        Collection<Integer> newValues = Arrays.asList(1, 2, 3);
        List<Integer> oldValues = map.replaceValues("wdnmd", newValues);

        List<Integer> removedValues = map.removeAll(map);

        return "baseListMultiMap";
    }

    /**
     * Multimap对象的淘汰机制是通过不同的接口来实现的。它们是RSetMultimapCache接口和RListMultimapCache接口，分别对应的是Set和List的Multimaps。
     *
     * 所有过期元素都是通过org.redisson.EvictionScheduler实例来实现定期清理的。
     * 为了保证资源的有效利用，每次运行最多清理100个过期元素。任务的启动时间将根据上次实际清理数量自动调整，间隔时间趋于1秒到2小时之间。
     * 比如该次清理时删除了100条元素，那么下次执行清理的时间将在1秒以后（最小间隔时间）。一旦该次清理数量少于上次清理数量，时间间隔将增加1.5倍。
     * @return
     */
    public Object evictionMultimap(){
        RSetMultimapCache<String, String> multimap = redisson.getSetMultimapCache("myMultimap");
        multimap.put("1", "a");
        multimap.put("1", "b");
        multimap.put("1", "c");

        multimap.put("2", "e");
        multimap.put("2", "f");

        multimap.expireKey("2", 10, TimeUnit.MINUTES);
        return "evictionMultimap";
    }





}
