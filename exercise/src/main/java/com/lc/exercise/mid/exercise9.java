package com.lc.exercise.mid;

public class exercise9 {

    public int nthSuperUglyNumber(int n, int[] primes) {

        int[] dp = new int[n];
        dp[0] = 1;

        int k = primes.length;
        // 没看出
        int[] index = new int[k];

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (min > dp[index[j]] * primes[j]) {
                    min = dp[index[j]] * primes[j];
                }
                dp[i] = min;
            }
            for (int j = 0; j < k; j++) {
                if (min == dp[index[j]] * primes[j]) {
                    index[j]++;
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

    }

}
