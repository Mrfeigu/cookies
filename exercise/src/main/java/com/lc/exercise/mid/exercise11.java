package com.lc.exercise.mid;

public class exercise11 {


    public static int sumNums(int n) {
        if(n == 1){
            return 1;
        }
        return sumNums(n-1) + n;
    }

    public static void main(String[] args) {
        System.out.println(sumNums(10));
    }

}
