package com.lc.exercise.easy;

/**
 * 最大子连续序列的最大和（贪心做法），还有线性树做法不太会
 *
 */
public class exercise2 {

    public int maxSubArray(int[] nums) {
        int max = nums[0], pre = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
    }

    public static void main(String[] args){
        int[] arr = {-2,1,-3,4,-1,2};
        System.out.println(new exercise2().maxSubArray(arr));
    }

}
