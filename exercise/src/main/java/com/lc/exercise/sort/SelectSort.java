package com.lc.exercise.sort;

/**
 * @author linzhenghui
 * @date 2020/12/8
 */
public class SelectSort {


    public static void selectSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int index = i - 1;
            for (int j = index + 1; j < arr.length; j++) {
                if(arr[index] > arr[j]) {
                    index = j;
                }
            }
            int swap = arr[index]; arr[index] = arr[i - 1]; arr[i - 1] = swap;
        }
    }


    public static void main(String[] args) {
        int[] arr = {11,2,6,16,32,0,10, -6, -10, 80, 54, 67, 10};
        selectSort(arr);
        System.out.println("ending...");
    }




}
