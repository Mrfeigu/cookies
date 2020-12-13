package com.lc.exercise.hard;


import java.util.LinkedList;
import java.util.List;

/**
 * 编辑距离
 *
 * @author linzhenghui
 * @date 2020/07/22
 */
public class exercise9 {


    public int minDistance(String word1, String word2) {

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // 初始化
        dp[0][0] = 0;
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }


    public static void main(String[] args) {

        int i = new exercise9().minDistance("horse", "ros");
        System.out.println(i);
        System.out.println("ending..");

    }



}
