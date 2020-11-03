package com.lc.exercise.hard;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 失败尝试，常规的回溯流程是会导致排序错误
 *
 *
 * @author linzhenghui
 * @date 2020/07/22
 */
public class exercise7 {

    private StringBuilder builder;

    private int index;

    /**
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        int[] arr = new int[n];
        for (int i = 1; i <= arr.length; i++) {
            arr[i-1] = i;
        }
        getPermutationHelp(arr, n, k, 0);
        String s = builder.toString();
        builder.replace(0, builder.length(), "");
        return s;
    }

    private void getPermutationHelp(int[] arr, int n, int k, int level){

        if(level >= n - 1){
            if(k - 1 == index){
                builder = new StringBuilder();
                for (int i = 0; i < arr.length; i++) {
                    builder.append(arr[i]);
                }
            }
            printArr(arr);
            index++;
            return;
        }

        for (int i = level; i < arr.length; i++) {
            int swap = arr[i]; arr[i] = arr[level]; arr[level] = swap;
            getPermutationHelp(arr, n, k, level + 1);
            swap = arr[i]; arr[i] = arr[level]; arr[level] = swap;
        }

    }

    public static void printArr(int[] arr){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : arr) {
            stringBuilder.append(i);
        }
        System.out.println(stringBuilder.toString());
    }


    public static void main(String[] args) {

        new exercise7().getPermutation(3, 5);

    }

}
