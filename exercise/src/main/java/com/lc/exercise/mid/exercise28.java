package com.lc.exercise.mid;

import java.util.*;

/**
 * 这题不会.....好丢人
 *
 * 按照贪心的思想，就是每次保持最远可到达
 *
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise28 {

    public boolean canJump(int[] nums) {
        int maxJump = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > maxJump) return false;
            if(i + nums[i] > maxJump) maxJump = nums[i] + i;
        }
        return true;
    }

    public static void main(String[] args){
        int []nums = {3,2,1,0,4};
        System.out.println(new exercise28().canJump(nums));
    }

}
