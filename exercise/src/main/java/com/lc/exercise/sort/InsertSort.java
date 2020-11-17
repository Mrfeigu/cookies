package com.lc.exercise.sort;

/**
 * @author linzhenghui
 * @date 2020/11/17
 */
public class InsertSort {

    /**
     * 插入排序
     * @param arr
     */
    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if(arr[j] <= temp) {
                    continue;
                }
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 22, 33, 2, 4, 54, 9, 8};

        insertSort(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
