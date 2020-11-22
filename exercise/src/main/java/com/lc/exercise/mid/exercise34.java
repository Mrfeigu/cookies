package com.lc.exercise.mid;

/**
 *
 */
public class exercise34 {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 1;
        }

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        // 初始化dp
        for (int i = 0; i < dp.length; i++) {
            if(obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }

        for (int i = 0; i < dp[0].length; i++) {
            if(obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }

        // dp计算
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if(obstacleGrid[i][j] == 1) {
                    continue;
                }

                if(obstacleGrid[i][j - 1] == 1) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                if(obstacleGrid[i - 1][j] == 1) {
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }

                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }




    public static void main(String[] args) {
        int[][] arr = {{0}};
        System.out.println(new exercise34().uniquePathsWithObstacles(arr));
    }


}
