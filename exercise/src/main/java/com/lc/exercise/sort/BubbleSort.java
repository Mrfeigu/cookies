package com.lc.exercise.sort;

/**
 * @author linzhenghui
 * @date 2020/11/17
 */
public class BubbleSort {


    /**
     * 传统冒泡
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= arr.length - i; j++) {
                if (arr[j] > arr[j - 1]) {
                    continue;
                }
                int swap = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = swap;
            }
        }
    }


    /**
     * 外层循环优化
     * @param arr
     */
    public static void bubbleSortOptimize1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int flag = 0;
            for (int j = 1; j <= arr.length - i; j++) {
                if (arr[j] > arr[j - 1]) {
                    continue;
                }
                int swap = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = swap;
                flag = 1;
            }
            if(flag == 0) {
                return;
            }
        }
    }

    /**
     * 内层优化(必须含有外层优化)
     * @param arr
     */
    public static void bubbleSortOptimize2(int[] arr) {
        int jSize = arr.length - 1;
        int pos = arr.length - 1;
        for (int i = 1; i < arr.length; i++) {
            int flag = 0;

            for (int j = 1; j <= jSize; j++) {
                if (arr[j] > arr[j - 1]) {
                    continue;
                }
                int swap = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = swap;
                pos = j;
                flag = 1;
            }
            if(flag == 0) {
                return;
            }
            jSize = pos;
        }
    }



    public static void main(String[] args) {
        int[] arr = {0, 8, 123, 312, 33, 4, 3, 2, 1};
        bubbleSortOptimize2(arr);
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }


}
