package com.delicacy.cookies.mult;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

import java.util.Set;

/**
 * http://ifeve.com/google-guava-newcollectiontypes/
 * RangeSet
 *
 * RangeSet描述了一组不相连的、非空的区间。当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class RangeSetUtils {

    /**
     * TreeRangeSet
     */
    private static RangeSet<Integer> rangeSet = TreeRangeSet.create();

    public static void main(String[] args) {

        // 区间类，解决区间问题首选
        Range<Integer> closed = Range.closed(1, 10);
        boolean contains = closed.contains(3);
        Integer integer = closed.upperEndpoint();
        Integer integer1 = closed.lowerEndpoint();
        boolean b = closed.hasUpperBound();
        boolean b1 = closed.hasLowerBound();

        rangeSet.add(closed);
        rangeSet.add(Range.openClosed(10, 20));
        rangeSet.add(Range.openClosed(30, 60));

        //用Set<Range<C>>表现RangeSet, 返回的是区间合并后的数据
        Set<Range<Integer>> ranges = rangeSet.asRanges();
        // 返回RangeSet的补集
        RangeSet<Integer> complement = rangeSet.complement();
        // 返回RangeSet与给定Range的交集视图。
        RangeSet<Integer> integerRangeSet = rangeSet.subRangeSet(Range.openClosed(15,30));

        // 返回包含给定元素的区间；若没有这样的区间，则返回null。
        Range<Integer> integerRange = rangeSet.rangeContaining(20);
        // 判断RangeSet中是否有任何区间包含给定元素。
        boolean b2 = rangeSet.contains(3);
        // 判断RangeSet中是否有任何区间包括给定区间。
        boolean encloses = rangeSet.encloses(Range.openClosed(5, 9));
        // 返回包括RangeSet中所有区间的最小区间
        Range<Integer> span = rangeSet.span();


        System.out.println("ending...");

    }


}
