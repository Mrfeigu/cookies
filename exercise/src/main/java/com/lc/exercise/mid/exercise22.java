package com.lc.exercise.mid;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise22 {


    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if(nums.length <= 0) return new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        helpPermute(numList, 0, nums.length);
        return resultList;
    }

    public void helpPermute(List<Integer> nums, int level, int max){
        if(level >= max - 1){
            resultList.add(new ArrayList<>(nums));
            return;
        }
        for (int i = level; i < nums.size(); i++) {
            int swap = nums.get(i); nums.set(i, nums.get(level)); nums.set(level, swap);
            helpPermute(nums, level + 1, max);
            swap = nums.get(i); nums.set(i, nums.get(level)); nums.set(level, swap);
        }

    }


    public static void main(String[] args){
        int[] nums = {1,2,3};
        List<List<Integer>> permute = new exercise22().permute(nums);
        System.out.println("ending...");
    }

}
