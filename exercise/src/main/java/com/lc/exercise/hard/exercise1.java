package com.lc.exercise.hard;


import java.util.ArrayList;
import java.util.List;

public class exercise1 {


    public int firstMissingPositive(int[] nums) {
        if(nums.length < 1) return 1;
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }

        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if(!numList.contains(i)){
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args){
        int[] arr = {7,8,9,11,12};
        int i = new exercise1().firstMissingPositive(arr);
        System.out.println("ending...");
    }

}
