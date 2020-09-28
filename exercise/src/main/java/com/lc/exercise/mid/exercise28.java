package com.lc.exercise.mid;

import java.util.*;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise28 {

    public boolean canJump(int[] nums) {
        int size = nums.length;
        int index = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {

            for (int j = index + 1; j <= index + nums[index]; j++) {
                list.add(nums[j]);
            }
            list.sort(Comparator.comparingInt(o->o));

        }





        return false;
    }

    public static void main(String[] args){

    }

}
