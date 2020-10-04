package com.delicacy.cookies.redisson.distribute.utils;


import org.redisson.api.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/redisson/redisson/wiki/7.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%9B%86%E5%90%88
 *
 * 基于Redis的Redisson的分布式Set结构的RSet Java对象实现了java.util.Set接口。
 * 通过元素的相互状态比较保证了每个元素的唯一性。
 * 该对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
 *
 * @author feigu
 * @date 2020/9/29
 */

@Service
public class SetDemoService {

    @Resource
    private RedissonClient redisson;


    public Object originDemo(){

        RSet<String> set = redisson.getSet("anySet");
        set.add("大哥大嫂过年好");
        set.remove("大哥大嫂过年好");
        return "originDemo";

    }

    /**
     * 目前的Redis自身并不支持Set当中的元素淘汰，因此所有过期元素都是通过org.redisson.EvictionScheduler实例来实现定期清理的。
     * 为了保证资源的有效利用，每次运行最多清理100个过期元素。任务的启动时间将根据上次实际清理数量自动调整，间隔时间趋于1秒到2小时之间。
     * 比如该次清理时删除了100条元素，那么下次执行清理的时间将在1秒以后（最小间隔时间）。
     * 一旦该次清理数量少于上次清理数量，时间间隔将增加1.5倍。
     * @return
     */
    public Object overdueFailure(){
        RSetCache<String> set = redisson.getSetCache("anySet");
        // ttl = 10 seconds
        set.add("让子弹再飞一会", 10, TimeUnit.SECONDS);
        return "overdueFailure";
    }

    /**
     * 有序集（SortedSet）
     *
     * 基于Redis的Redisson的分布式RSortedSet Java对象实现了java.util.SortedSet接口。
     * 在保证元素唯一性的前提下，通过比较器（Comparator）接口实现了对元素的排序。
     * @return
     */
    public Object sortedSetDemo(){
        RSortedSet<Integer> set = redisson.getSortedSet("anySet");
        set.trySetComparator(new MyComparator()); // 配置元素比较器
        set.add(3);
        set.add(1);
        set.add(2);

        set.removeAsync(0);
        set.addAsync(5);

        return "sortedSetDemo";
    }

    /**
     * 基于Redis的Redisson的分布式RScoredSortedSet Java对象是一个可以按插入时指定的元素评分排序的集合。它同时还保证了元素的唯一性。
     * @return
     */
    public Object scoredSortedSetDemo(){

        RScoredSortedSet<String> set = redisson.getScoredSortedSet("simple");

        set.add(0.13, "真");
        set.addAsync(0.251, "不");
        set.add(0.302, "戳");
        set.add(0.302, "啊");

        String s = set.pollFirst();
        String s1 = set.pollLast();

        // 获取元素在集合中的位置
        int index = set.rank("啊");
        // 获取元素的评分
        Double score = set.getScore("戳");

        return "scoredSortedSetDemo";
    }


    /**
     * 基于Redis的Redisson的分布式RLexSortedSet Java对象在实现了java.util.Set<String>接口的同时，
     * 将其中的所有字符串元素按照字典顺序排列。它公式还保证了字符串元素的唯一性。
     * @return
     */
    public Object rLexSortedSetDemo(){

        RLexSortedSet set = redisson.getLexSortedSet("simple");
        set.add("d");
        set.addAsync("e");
        set.add("f");

        set.rangeTail("d", false);

        set.countHead("e", false);
        set.range("d", true, "z", false);


        return "lexSortedSetDemo";
    }

    public static class MyComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            return (int)o1 - (int)o2;
        }
    }

}
