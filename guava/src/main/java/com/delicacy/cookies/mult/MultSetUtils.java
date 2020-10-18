package com.delicacy.cookies.mult;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

/**
 * http://ifeve.com/google-guava-newcollectiontypes/
 *
 * Multiset不能保证线程安全，当成一个高级set使用就好了
 *
 * 没有元素顺序限制的ArrayList<E>
 * Map<E, Integer>，键为元素，值为计数
 *
 * 所有Multiset实现的内存消耗随着不重复元素的个数线性增长。
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class MultSetUtils {

    /**
     * HashMultiset， TreeMultiset，LinkedHashMultiset，ConcurrentHashMultiset
     */
    private static Multiset<String> multiset = HashMultiset.create();

    /**
     * 带区间筛选 latencies.subMultiset(0,BoundType.CLOSED, 100, BoundType.OPEN).size()
     */
    private static SortedMultiset<String> sortedMultiset = TreeMultiset.create();

    public static void main(String[] args) {
        // api测试
        multiset.add("潮汐之灵");
        multiset.add("潮汐之灵");

        multiset.add("四叶草");
        multiset.add("四叶草");
        multiset.add("四叶草");

        multiset.add("飞雷神");
        multiset.add("RE");
        multiset.add("至高之拳");

        int count = multiset.count("潮汐之灵");
        int count2 = multiset.count("四叶草");
        int size = multiset.size();
        int size1 = multiset.elementSet().size();
        boolean b = multiset.contains("飞雷神");
        boolean o = multiset.remove("潮汐之灵");
        int count3 = multiset.remove("四叶草", 3);

        System.out.println("ending..");
    }


}
