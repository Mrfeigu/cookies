package com.lc.exercise.mid;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 旋转矩阵
 * 观察可以发现，反转矩阵，按列反转就可以得到正确的旋转结果
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise24 {

    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }
    }

    
    public static void main(String[] args){
        //int[][] res = {{5, 1, 9,11},{2, 4, 8,10},{13, 3, 6, 7},{15,14,12,16}};
        int[][] res = {{1,2,3},{4,5,6},{7,8,9}};
        new exercise24().rotate(res);
    }

}
