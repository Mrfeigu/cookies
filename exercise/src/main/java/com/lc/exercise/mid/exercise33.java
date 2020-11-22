package com.lc.exercise.mid;


import java.util.Arrays;

/**
 *
 */
public class exercise33 {


    /**
     * 动态规划
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsV2(int m, int n) {
        int[][] dp = new int[m][n];
        // 初始值
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        // 动规
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 递归方法，实在超时
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int pathCount = 0;
        uniquePathsHelp(m - 1, n - 1, 0, 0, pathCount);
        return pathCount;
    }

    private void uniquePathsHelp(int m, int n, int x, int y, int result) {
        if(x == m && y == n) {
            result++;
            return;
        }
        if(x < m) {
            uniquePathsHelp(m, n, x + 1, y, result);
        }
        if(y < n) {
            uniquePathsHelp(m, n, x, y + 1, result);
        }
    }


    public static void main(String[] args) {

        System.out.println(new exercise33().uniquePathsV2(3, 2));

    }


}
