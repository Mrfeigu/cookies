package com.lc.exercise.mid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 1,2,3,6,9,8,7,4,5
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise27 {

    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length == 0) return new ArrayList<>();
        List<Integer> result = new LinkedList<>();
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1, sum = matrix.length * matrix[0].length;
        int index = 0;
        while(index < sum) {

            if (top <= bottom) {
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                    index++;
                }
                top++;
            }

            if (right >= left) {
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                    index++;
                }
                right--;
            }

            if (bottom >= top) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                    index++;
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                    index++;
                }
                left++;
            }

        }

        return result;
    }

    public static void main(String[] args){
        int [][] matrix = {{2,5,8},{4,0,-1}};
        int [][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        new exercise27().spiralOrder(matrix2);
    }

}
