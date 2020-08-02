package com.lc.exercise.mid;

import java.util.*;

/**
 * Pow(x, n)
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise26 {

    public double myPow(double x, int n) {
        return n >= 0 ? helpMyPowV2(x, n) : -1 / helpMyPowV2(x, n);
    }

    private double helpMyPow(double x, int n){
        if(n == 0) return 1.0;
        double y = helpMyPow(x, n / 2);
        // 奇数都会少一个
        return n % 2== 0 ? y * y : y * y * x;
    }

    /**
     * 这是真的难理解
     * 题解：https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
     * @param x
     * @param n
     * @return
     */
    private double helpMyPowV2(double x, int n){
        double result = 1.0;
        double temp = x;
        while(n > 0){
            if(n % 2 == 1)result *= temp;
            temp *= temp;
            n /= 2;
        }
        return result;
    }




    public static void main(String[] args){
        double v = new exercise26().myPow(2.00000, 10);
        System.out.println("ending...");
    }

}
