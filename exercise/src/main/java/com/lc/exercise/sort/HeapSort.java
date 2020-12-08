package com.lc.exercise.sort;

/**
 * @author linzhenghui
 * @date 2020/12/8
 */
public class HeapSort {

    public static void heapSort(int[] arr) {

    }

    /**
     * left = top * 2 + 1;
     * right = top * 2 + 2;
     *
     * top = (left - 1) / 2; left 是奇数
     * top = (right - 2) / 2; right 是偶数
     *
     * @param arr
     * @param begin
     */
    public static void maxHeap(int[] arr, int begin) {
        int swap = 0;
        for (int i = arr.length - 1; i > begin; i--) {
            // 交换
            if(i % 2 == 1  && (i - 1) /2 >= begin && arr[(i - 1) / 2] < arr[i]) {
                swap = arr[(i - 1) / 2]; arr[(i - 1) / 2] = arr[i]; arr[i] = swap;
            }
            if(i % 2 == 0 && (i - 2) /2 >= begin && arr[(i - 2) /2] < arr[i]) {
                swap = arr[(i - 1) / 2]; arr[(i - 1) / 2] = arr[i]; arr[i] = swap;
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7};
        maxHeap(arr, 1);
        System.out.println("ending...");

    }




}
