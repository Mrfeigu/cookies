package com.lc.exercise.mid;

public class exercise4 {



    public static int numWays(int n) {
        if(n <= 2){
            return 1;
        }
        int dp[] = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(numWays(40));
    }


}
