package com.delicacy.cookies.mult;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.*;

/**
 * http://ifeve.com/google-guava-collectionutilities/
 * guava提供的一些工具
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class GuavaCollectionUtils {

    private static BiMap<String, String> biMap = HashBiMap.create();

    /**
     * List的demo
     */
    public static void listDemo(){
        // 分割List
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        // size 不能等于0, 会抛出异常
        // List<List<Integer>> partition = Lists.partition(list, 0);
        List<List<Integer>> partition1 = Lists.partition(list, 3);
        List<List<Integer>> partition2 = Lists.partition(list, 10);
        // 反转List， 如果List是不可变的， 考虑改用ImmutableList.reverse()
        List<Integer> reverse = Lists.reverse(list);
        System.out.println("ending....");
    }


    public static void setDemo(){
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 9, 10);
        Set<Integer> set2 = Sets.newHashSet(1, 2, 3, 4, 5, 6, 7, 8);
        List<Set<Integer>> setList = Lists.newArrayList(set1, set2);
        // 返回并集
        Sets.SetView<Integer> union = Sets.union(set1, set2);
        // 将SetView拷贝到set中
        Set<Integer> setT = Sets.newHashSet();
        union.copyInto(setT);
        // 返回交集
        Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);
        // 返回set2中不包含set1的元素
        Sets.SetView<Integer> difference = Sets.difference(set1, set2);
        // 对称性（返回set1，set2的并集的补集）
        Sets.SetView<Integer> integers = Sets.symmetricDifference(set1, set2);
        // 返回笛卡尔积
        Set<List<Integer>> lists = Sets.cartesianProduct(setList);
        // 返回给定集合的所有子集
        Set<Set<Integer>> sets = Sets.powerSet(set1);
        System.out.println("ending....");
    }

    public static void mapDemo(){
        List<String> list = Lists.newArrayList("1", "22", "333");
        // 通过唯一的特性去生成map
        ImmutableMap<Integer, String> integerStringImmutableMap = Maps.uniqueIndex(list, String::length);

        Map<String, String> map1 = Maps.newHashMap();
        map1.put("1", "崔斯特");
        map1.put("2", "c");
        map1.put("3", "b");
        map1.put("4", "雷霆嘎巴巴");
        map1.put("5", "雷霆嘎巴");

        Map<String, String> map2 = Maps.newHashMap();
        map2.put("1", "崔斯特");
        map2.put("2", "c");
        map2.put("3", "b");
        map2.put("4", "雷霆嘎巴");
        map2.put("6", "雷霆嘎巴巴");

        MapDifference<String, String> difference = Maps.difference(map1, map2);
        // 两个Map中都有的映射项，包括匹配的键与值
        Map<String, String> stringStringMap = difference.entriesInCommon();
        // 键相同但是值不同值映射项。返回的Map的值类型为MapDifference.ValueDifference，以表示左右两个不同的值
        Map<String, MapDifference.ValueDifference<String>> stringValueDifferenceMap = difference.entriesDiffering();
        // 键只存在于左边Map的映射项(map1)
        Map<String, String> stringStringMap1 = difference.entriesOnlyOnLeft();
        // 键只存在右边Map的映射项(map2)
        Map<String, String> stringStringMap2 = difference.entriesOnlyOnRight();


        // 处理BiMap， 实际上就是一个加锁的装饰器 同 Collections.synchronizedMap(Map)
        BiMap<String, String> stringStringBiMap = Maps.synchronizedBiMap(biMap);
        // 处理BiMap，该方法返回了一个map的不可修改的视图umap， 为用户提供了一种生成只读容器的方法。如果尝试修改该容器umap, 将会抛出UnsupportedOperationException异常。 同Collections.unmodifiableMap(Map)
        BiMap<String, String> stringStringBiMap1 = Maps.unmodifiableBiMap(biMap);

        System.out.println("ending...");
    }


    public static void multisetsDemo(){

        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("1");
        multiset1.add("1");
        multiset1.add("1");
        multiset1.add("1");
        multiset1.add("1");
        multiset1.add("2");
        multiset1.add("3");
        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("1");
        multiset2.add("1");
        multiset2.add("1");
        // 对任意o，如果sub.count(o)<=super.count(o)，返回true
        boolean b = Multisets.containsOccurrences(multiset1, multiset2);// 这个要返回false
        // 对toRemove中的重复元素，仅在removeFrom中删除相同个数。
        boolean b1 = Multisets.removeOccurrences(multiset1, multiset2);
        // 修改removeFrom，以保证任意o都符合removeFrom.count(o)<=toRetain.count(o)
        boolean b2 = Multisets.retainOccurrences(multiset1, multiset2);
        // 返回两个multiset的交集
        Multiset<String> intersection = Multisets.intersection(multiset1, multiset2);

        // 返回Multiset的不可变拷贝，并将元素按重复出现的次数做降序排列
        ImmutableMultiset<String> strings = Multisets.copyHighestCountFirst(multiset1);
        // 返回Multiset的只读视图
        Multiset<String> strings1 = Multisets.unmodifiableMultiset(multiset1);

        // SortedMultiset<E> es = Multisets.unmodifiableSortedMultiset(multiset1);

        System.out.println("ending...");
    }

    public static void multimapsDemo(){

        // 有一组对象，它们有共同的特定属性，我们希望按照这个属性的值查询对象，但属性值不一定是独一无二的。
        List<String> list = Lists.newArrayList("1", "22", "333");
        ImmutableListMultimap<Integer, String> index = Multimaps.index(list, String::length);

        // 鉴于Multimap可以把多个键映射到同一个值（译者注：实际上这是任何map都有的特性），也可以把一个键映射到多个值，反转Multimap也会很有用。
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.putAll("b", Ints.asList(2, 4, 6));
        multimap.putAll("a", Ints.asList(4, 2, 1));
        multimap.putAll("c", Ints.asList(2, 5, 3));
        //注意我们选择的实现，因为选了TreeMultimap，得到的反转结果是有序的
        TreeMultimap<Integer, String> inverse = Multimaps.invertFrom(multimap, TreeMultimap.create());

        // 与Multimaps.invertFrom结合使用，可以把多对一的Map反转为一对多的Multimap
        HashMap<Integer, Integer> map = Maps.newHashMap();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 4);
        map.put(4, 4);
        map.put(5, 4);
        SetMultimap<Integer, Integer> integerIntegerSetMultimap = Multimaps.forMap(map);
        System.out.println("ending...");
    }


    public static void main(String[] args) {

        multimapsDemo();

    }

}
