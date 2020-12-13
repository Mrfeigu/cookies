package com.lc.exercise.mid;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

/**
 * 简化Unix路径
 */
public class exercise37 {

    public void setZeroes(int[][] matrix) {

        List<Pair<Integer, Integer>> list = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    list.add(new Pair<>(i, j));
                }
            }
        }

        for (Pair<Integer, Integer> pair : list) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[pair.getKey()][i] = 0;
            }

            for (int i = 0; i < matrix.length; i++) {
                matrix[i][pair.getValue()] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        new exercise37().setZeroes(arr);
        System.out.println("ending...");
    }

}
