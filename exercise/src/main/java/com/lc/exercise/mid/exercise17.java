package com.lc.exercise.mid;


import java.util.Arrays;

public class exercise17 {


    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1) return;
        int left = -1, right = -1;
        for (int j = nums.length - 1; j > 0 ; j--) {
            if(nums[j] > nums[j-1]){
                left = j - 1;
                break;
            }
        }

        if(left == -1){
            Arrays.sort(nums);
            return;
        }

        for (int i = nums.length - 1; i > 0 ; i--) {
            if(nums[left] < nums[i]){
                right = i;
                swap(nums, left, right);
                reverse(nums, left + 1);
                return;
            }
        }
    }


    private void reverse(int[] nums, int start){
        int i = start, j = nums.length - 1;
        while(i < j){
            swap(nums, i, j);
            i++;
            j--;
        }

    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,5};
        new exercise17().nextPermutation(nums);
        System.out.println("ending...");
    }

}
