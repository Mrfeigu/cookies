package com.lc.exercise.sort;

/**
 * 这种要移位，保证不了稳定
 *
 * @author linzhenghui
 * @date 2020/12/8
 */
public class QuickSort {


    public static void quickSort(int[] arr) {
        quickSortHelp(arr, 0, arr.length - 1);
    }

    public static void quickSortHelp(int[] arr, int left, int right) {
        if(left >= right) {
            return;
        }
        int refer = left;
        for (int i = left + 1; i <= right; i++) {
            if(arr[refer] > arr[i]) {
                int target = arr[i];
                // 填充
                int i1 = i - refer;
                while(i1 > 0) {
                    arr[i1 + refer] = arr[i1 + refer - 1];
                    i1--;
                }
                // 替换
                arr[refer] = target;
                refer += 1;
            }
        }

        quickSortHelp(arr, left, refer - 1);
        quickSortHelp(arr, refer + 1, right);
    }

    public static void main(String[] args) {
        int[] arr = {8,10,2,5,7};
        quickSort(arr);
        System.out.println("ending...");
    }




}
