package com.lc.exercise.hard;

import java.util.*;

/**
 * N皇后
 * @author linzhenghui
 * @date 2020/07/22
 */
public class exercise5 {

    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n){
        Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i++){
            arr[i] = i + 1;
        }
        helpSolveNQueens(n, 0, arr);
        return result;
    }

    private void helpSolveNQueens(int n, int level, Integer [] arr){
        if(level >= n - 1){
            List<String> resultList = new ArrayList<>();
            for (Integer integer : arr) {
                resultList.add(buildStr(n, integer));
            }
            result.add(resultList);
            return;
        }

        for (int i = level; i < n; i++) {
            int temp = arr[i]; arr[i] = arr[level]; arr[level] = temp;
            if(i == level){
                helpSolveNQueens(n, level + 1, arr);
            }
            if(
                    (i-1 >= 0 && Math.abs(arr[i] - arr[i-1]) >= 2 && i+1 < n && Math.abs(arr[i+1] - arr[i]) >= 2)
                            || (i == 0 && i+1 < n && Math.abs(arr[i+1] - arr[i]) >= 2)
                            || (i == n-1 && i-1 >= 0 && Math.abs(arr[i] - arr[i-1]) >= 2)
            )
            {
                helpSolveNQueens(n, level + 1, arr);
            }
            temp = arr[i]; arr[i] = arr[level]; arr[level] = temp;

        }

    }

    /**
     * 建立Queens位置的String
     * @param n
     * @param index
     * @return
     */
    private String buildStr(int n, int index){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if(i == index){
                stringBuilder.append("Q");
            }else{
                stringBuilder.append(".");
            }
        }
        return stringBuilder.toString();
    }



    public static void main(String[] args) {
        new exercise5().solveNQueens(4);
    }

}
