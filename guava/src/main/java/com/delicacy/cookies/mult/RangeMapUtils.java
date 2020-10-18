package com.delicacy.cookies.mult;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import java.util.Map;

/**
 * http://ifeve.com/google-guava-newcollectiontypes/
 * RangeMap
 * RangeMap描述了”不相交的、非空的区间”到特定值的映射。和RangeSet不同，RangeMap不会合并相邻的映射，即便相邻的区间映射到相同的值。
 *
 * 这个有点方便，用得复杂容易出错
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class RangeMapUtils {

    private static RangeMap<Integer, String> rangeMap = TreeRangeMap.create();


    public static void main(String[] args) {

        //{[1,10] => "foo"}
        rangeMap.put(Range.closed(1, 10), "foo");
        //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar");
        //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
        rangeMap.put(Range.open(10, 20), "foo");
        //{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}
        rangeMap.remove(Range.closed(5, 11));

        // 用Map<Range<K>, V>表现RangeMap。这可以用来遍历RangeMap。
        Map<Range<Integer>, String> rangeStringMap = rangeMap.asMapOfRanges();
        // 用RangeMap类型返回RangeMap与给定Range的交集视图。
        RangeMap<Integer, String> integerStringRangeMap = rangeMap.subRangeMap(Range.closed(4, 11));
        String s = integerStringRangeMap.get(5);

        System.out.println("ending...");
    }


}
