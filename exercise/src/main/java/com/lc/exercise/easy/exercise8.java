package com.lc.exercise.easy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * +1
 *
 * @author linzhenghui
 * @date 2020/10/20
 */
public class exercise8 {

    public int[] plusOne(int[] digits) {
        int index = digits.length - 1;
        for (int i = 0; i < digits.length; i++) {
            if(digits[index - i] != 9) {
                digits[index - i] += 1;
                return digits;
            }

            if(index - i == 0) {
                return lastRes(digits);
            }

            digits[index - i] = 0;
        }
        return digits;
    }

    public int[] lastRes(int[] digits) {
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {9};
        int[] ints = new exercise8().plusOne(arr);
        System.out.println("ending...");
    }

}
