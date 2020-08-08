package com.lc.exercise.mid;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class exercise21 {


    private List<List<Integer>> resultList = new ArrayList<>();


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length < 1) return resultList;
        Arrays.sort(candidates);
        helpCombinationSum2(candidates, target, new Stack<>(), 0, 0);
        return resultList;
    }

    public void helpCombinationSum2(int[] candidates, int target, Stack<Integer> stack, int level, int sum){
        if(sum >= target || level >= candidates.length){
            if(sum == target){
                resultList.add(new ArrayList<>(stack));
            }
            return;
        }

        for (int i = level; i < candidates.length; i++) {
            if(level != i && candidates[i - 1] == candidates[i]){
                continue;
            }
            stack.push(candidates[i]);
            helpCombinationSum2(candidates, target, stack, i + 1, sum + candidates[i]);
            stack.pop();
        }
    }


    public static void main(String[] args){
        int[] arr = {2,5,2,1,2};
        List<List<Integer>> lists = new exercise21().combinationSum2(arr, 5);
        System.out.println("ending..");
    }

}
