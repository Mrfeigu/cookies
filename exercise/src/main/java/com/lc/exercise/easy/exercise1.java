package com.lc.exercise.easy;


import java.util.ArrayList;
import java.util.List;

public class exercise1 {

    public String countAndSay(int n){
        if(n <= 1) return "1";
        String str = countAndSay(n-1);
        StringBuilder stringBuilder = new StringBuilder();
        int tempRes = 1;
        char tempChar;
        for (int i = 1; i <= str.length(); i++) {
            while(i < str.length() && str.charAt(i) == str.charAt(i-1)){
                tempRes++;
                i++;
            }
            tempChar = str.charAt(i-1);
            stringBuilder.append(tempRes).append(tempChar);
            tempRes = 1;
        }
        return stringBuilder.toString();
    }



    public static void main(String[] args){
        String s = new exercise1().countAndSay(5);
        System.out.println("ending...");
    }

}
