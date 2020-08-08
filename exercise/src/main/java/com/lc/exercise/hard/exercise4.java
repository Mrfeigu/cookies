package com.lc.exercise.hard;

/**
 * 跳跃游戏2
 * @author linzhenghui
 * @date 2020/07/22
 */
public class exercise4 {

    /**
     * 贪心
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int step = 0;
        int maxPostion = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            // 找出每个跳得最远的一步，这个是真的秀
            maxPostion = Math.max(maxPostion, i + nums[i]);
            if(i == end){
                end = maxPostion;
                step++;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,3,1,1,4,5};
        int jump = new exercise4().jump(arr);
        System.out.println("ending...");
    }

}
