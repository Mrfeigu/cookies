package com.lc.exercise.easy;

/**
 * x 的平方根
 *
 * @author linzhenghui
 * @date 2020/10/20
 */
public class exercise10 {

    /**
     * 二分查找
     * @param x
     * @return
     */
    public int mySqrt(int x) {

        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new exercise10().mySqrt(2147395599);
        System.out.println(i);
    }



}
