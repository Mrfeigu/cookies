package com.lc.exercise.mid;

import java.util.*;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise29 {
    public static int[][] merge(int[][] intervals) {
        // 这个题目的精髓就在于这个排序了
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        if(intervals.length < 1){
            return new int[0][];
        }
        // 这个数据结构也比较方便
        LinkedList<int[]> list = new LinkedList<>();
        list.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] pop = list.getLast();
            int[] interval = intervals[i];
            if(pop[0] == interval[0] || pop[0] < interval[0] && interval[0] <= pop[1]){
                pop[1] = Math.max(pop[1], interval[1]);
            }else {
                list.add(interval);
            }
        }
        int[][] res = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args){
        int [][] arr = {{1,3},{2,6},{8,10},{15,18}};
        merge(arr);
    }

}
