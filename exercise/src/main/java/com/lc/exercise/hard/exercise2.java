package com.lc.exercise.hard;

import java.util.Stack;

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


    /**
     * 堆做法 boom，boom，boom(至少我是不会这样操作的)
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int res = 0, current = 0;
        Stack<Integer> stack = new Stack<>();
        while(current < height.length){
            while (!stack.isEmpty() && height[stack.peek()] < height[current]) {
                int compare = stack.pop();
                if(stack.isEmpty())break;
                int distnct = current - stack.peek() - 1;
                res += (Math.min(height[stack.peek()], height[current]) - height[compare]) * distnct;
            }
            stack.push(current++);
        }
        return res;
    }

    /**
     * 双指针，反正就很妙
     * @param height
     * @return
     */
    public int trap4(int[] height){
        int res = 0, left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while(left < right){
            if(height[left] < height[right]){
                if(height[left] > leftMax) leftMax = height[left]; else res += (leftMax - height[left]);
                left++;
            }else{
                if(height[right] > rightMax) rightMax = height[right]; else res += (rightMax - height[right]);
                right--;
            }
        }
        return res;
    }




    public static void main(String[] args){
        int[] arr = {2,1,0,2};
        int trap = new exercise2().trap3(arr);
        System.out.println(trap);
        System.out.println("ending...");
    }

}
