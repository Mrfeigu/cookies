package com.lc.exercise.hard;

public class exercise2 {

    /**
     * 中心开花boom boom boom
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int maxLeft = 0, maxRight = 0;
            for (int j = i; j >= 0 ; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            res += Math.min(maxLeft, maxRight) - height[i];
        }
        return res;
    }

    /**
     * 动态规划？！思想还是上面的思想
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if(height.length < 1) return 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int res = 0;
        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            res += (Math.min(leftMax[i] ,rightMax[i]) - height[i]);
        }
        return res;
    }



    public static void main(String[] args){
        int[] arr = {2,0,2};
        int trap = new exercise2().trap2(arr);
        System.out.println(trap);
        System.out.println("ending...");
    }

}
