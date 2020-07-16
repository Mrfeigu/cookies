package com.lc.exercise.hard;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class exercise2 {

    public int trap(int[] height) {
        LinkedList<Integer> heightList = new LinkedList<>();
        for (int i : height) heightList.add(i);
        int waterSum = 0, tempWaterSum = 0, leftPillar = 0;
        for(int i = 0; i < heightList.size(); i++) {

        }
        return waterSum;
    }




    public static void main(String[] args){
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = new exercise2().trap(arr);
        System.out.println("ending...");
    }

}
