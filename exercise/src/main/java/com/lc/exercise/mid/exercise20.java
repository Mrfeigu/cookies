package com.lc.exercise.mid;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class exercise20 {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates.length < 1) return resultList;
        Arrays.sort(candidates);
        helpCombinationSum(candidates, target, 0, 0, new Stack<>());
        return resultList;
    }

    private void helpCombinationSum(int[] candidates, int target, int level, int sum, Stack<Integer> resultStack){
        if(sum >= target || level >= candidates.length){
            if(sum == target){
                resultList.add(new ArrayList<>(resultStack));
            }
            if(!resultStack.isEmpty()) resultStack.pop();
            return;
        }

        int begin = level;
        for (int i = begin; i < candidates.length; i++) {
            resultStack.push(candidates[i]);
            helpCombinationSum(candidates, target, level, sum + candidates[i], resultStack);
            level++;
        }
        if(!resultStack.isEmpty()) resultStack.pop();
    }



    public static void main(String[] args){
        int[] arr = {};
        List<List<Integer>> lists = new exercise20().combinationSum(arr, 8);
        System.out.println("ending...");
    }

}
