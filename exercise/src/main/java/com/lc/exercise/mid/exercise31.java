package com.lc.exercise.mid;


/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 */
public class exercise31 {



    public static int[][] generateMatrix(int n) {
        if(n < 1) return new int[0][];
        int[][] res = new int[n][n];
        int size = n * n;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        int index = 1;
        while(index <= size){

            if(top <= bottom){
                for (int i = left; i <= right; i++) {
                    res[top][i] = index;
                    ++index;
                }
                ++top;
            }

            if(right >= left){
                for (int i = top; i <= bottom; i++) {
                    res[i][right] = index;
                    ++index;
                }
                --right;
            }

            if(bottom >= top){
                for (int i = right; i >= left; i--) {
                    res[bottom][i] = index;
                    ++index;
                }
                --bottom;
            }

            if(left <= right){
                for (int i = bottom; i >= top; i--) {
                    res[i][left] = index;
                    ++index;
                }
                ++left;
            }
        }
        return res;
    }



    public static void main(String[] args) {

        int[][] ints = generateMatrix(5);
        System.out.println("ending...");
    }




}
