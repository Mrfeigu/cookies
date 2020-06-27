package com.lc.exercise.mid;

import java.util.LinkedList;

public class exercise18 {

    private LinkedList<Integer> resultList = new LinkedList<>();

    public int[] searchRange(int[] nums, int target) {
        if(nums == null) return new int[]{-1, -1};
        helpSearchRange(nums, target, 0, nums.length-1);
        if(resultList.size() < 1) return new int[]{-1, -1};
        return new int[]{resultList.getFirst(), resultList.getLast()};
    }
    private void helpSearchRange(int[] nums, int target, int left, int right){
        if(left > right) return;
        int mid = left + (right - left) / 2;
        if(nums[mid] == target){
            if(resultList.isEmpty()) resultList.add(mid);
            if(mid < resultList.getFirst()) resultList.addFirst(mid);
            if(mid > resultList.getLast()) resultList.addLast(mid);
        }
        helpSearchRange(nums, target, mid+1, right);
        helpSearchRange(nums, target, left, mid-1);
    }

    public static void main(String[] args){
        int[] nums = {5,7,7,8,8,10};
        int[] ints = new exercise18().searchRange(nums, 8);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
        System.out.println("ending...");
    }

}
