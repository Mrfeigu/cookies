package com.lc.exercise.mid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 全排列 带重复
 * 使用保留栈去重
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise23 {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums == null || nums.length <= 0) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        helpPermuteUnique(list, 0, nums.length);
        return resultList;
    }


    public void helpPermuteUnique(List<Integer> nums, int level, int max){
        if(level >= max - 1){
            resultList.add(new ArrayList<>(nums));
            return;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = level; i < nums.size(); i++) {
            if(i != level && nums.get(i).equals(nums.get(level)) || stack.contains(nums.get(i))) continue;
            stack.add(nums.get(i));
            int swap = nums.get(i); nums.set(i, nums.get(level)); nums.set(level, swap);
            helpPermuteUnique(nums, level + 1, max);
            swap = nums.get(i); nums.set(i, nums.get(level)); nums.set(level, swap);
        }
    }



    public static void main(String[] args){

        int[] nums = {2,2,1,1};
        List<List<Integer>> lists = new exercise23().permuteUnique(nums);
        System.out.println("ending...");

    }

}
