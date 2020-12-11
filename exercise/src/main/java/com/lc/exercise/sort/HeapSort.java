package com.lc.exercise.sort;

/**
 * @author linzhenghui
 * @date 2020/12/8
 */
public class HeapSort {


    /**
     * left = top * 2 + 1;
     * right = top * 2 + 2;
     * <p>
     * top = (left - 1) / 2; left 是奇数
     * top = (right - 2) / 2; right 是偶数
     *
     * @param arr
     */
    public static void dui(int arr[]) {
        int len = arr.length - 1;
        for (int i = len; i > 0; i--) {
            //数组总长度/2-1可以获取到最后一个非叶子节点,这个是根据二叉树的N0 = N2 + 1;得来的
            for (int a = (i + 1) / 2 - 1; a >= 0; a--) {

                //求出堆中最大值，并浮上堆顶
                int length = i + 1;
                int left = -1;
                int right = -1;

                if (a * 2 + 1 < length) {
                    left = a * 2 + 1;
                }
                if (a * 2 + 2 < length) {
                    right = a * 2 + 2;
                }

                int swapR = arr[a];
                if (left != -1) {
                    if (arr[a] < arr[left]) {
                        arr[a] = arr[left];
                        arr[left] = swapR;
                        swapR = arr[a];
                    }
                }
                if (right != -1) {
                    if (arr[a] < arr[right]) {
                        arr[a] = arr[right];
                        arr[right] = swapR;
                    }
                }
            }

            int swap = arr[0];
            arr[0] = arr[i];
            arr[i] = swap;
        }
    }

    public static void main(String[] args) {

        int[] arr = {1, 5, 4, 2, 3, 7, 6};
        dui(arr);
        System.out.println("ending...");


    }


}
