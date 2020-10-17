package com.lc.exercise.hard;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 我的思路（执行比较慢，但是能通过）insert
 * 1：最左
 * 2：有交集
 * 3：循环
 * 4：整合
 *
 * 官方：贪心算法 insert2 gan
 * 从局部推广到全部
 *
 *
 *
 * @author linzhenghui
 * @date 2020/07/22
 */
public class exercise6 {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length < 1){
            int[][] res = new int[1][];
            res[0] = newInterval;
            return res;
        }
        LinkedList<int[]> res = new LinkedList<>();
        Collections.addAll(res, intervals);

        boolean flag = true;
        for (int i = 0; i < intervals.length; i++) {
            // 完全小于
            if(newInterval[1] < intervals[i][0]){
                res.add(i, newInterval);
                flag = false;
                break;
            }

            // 存在交集
            if(newInterval[1] >= intervals[i][0] && newInterval[1] <= intervals[i][1]
                    || newInterval[0] <= intervals[i][1] && newInterval[1] >= intervals[i][0]){
                res.get(i)[0] = Math.min(newInterval[0], intervals[i][0]);
                res.get(i)[1] = Math.max(newInterval[1], intervals[i][1]);
                flag = false;
                break;
            }
        }
        if(flag) res.addLast(newInterval);
        return merge(res.toArray(new int[res.size()][]));
    }

    /**
     * 整合区间
     * @param intervals
     * @return
     */
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


    /**
     * 就是直接add进去，然后进行分类
     * @param intervals
     * @return
     */
    public static int[][] insert2(int[][] intervals, int[] newInterval) {
        int[][] myIntervals = new int[intervals.length + 1][2];
        for (int i = 0; i < intervals.length; i++) {
            myIntervals[i] = intervals[i];
        }
        myIntervals[intervals.length] = newInterval;
        // 这个题目的精髓就在于这个排序了
        Arrays.sort(myIntervals, Comparator.comparingInt(o -> o[0]));
        // 这个数据结构也比较方便
        LinkedList<int[]> list = new LinkedList<>();
        list.add(myIntervals[0]);

        for (int i = 1; i < myIntervals.length; i++) {
            int[] pop = list.getLast();
            int[] interval = myIntervals[i];
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







    public static void main(String[] args) {
        int[][] a = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] b = {4,8};
        int[][] insert = insert2(a, b);
        System.out.println("ending...");
    }

}
