package com.lc.exercise.mid;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise29 {
    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        for (int i = 1; i < intervals.length; i++) {
            // todo




        }


        return null;
    }

    public static void main(String[] args){
        int [][] arr = {{1,3},{2,6},{8,10},{15,18}};
        merge(arr);
    }

}
