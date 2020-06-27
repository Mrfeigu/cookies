package com.lc.exercise.mid;

import java.util.HashSet;
import java.util.Set;

public class exercise6 {


    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int temp = n;
        while(!set.add(temp)){
            if(temp == 1){
                return true;
            }
            temp = getNext(temp);
        }
        return false;
    }

    public static void main(String[] args) {

    }

}
