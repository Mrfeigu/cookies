package com.lc.exercise.easy;

public class exercise5 {


    public static int lengthOfLastWord(String s) {
        if(s.length() < 1 || s.equals(" ")){
            return 0;
        }
        String[] s1 = s.split(" ");
        if(s1.length < 1){
            return 0;
        }
        return s1[s1.length - 1].length();
    }


    public static void main(String[] args){
        lengthOfLastWord("");
    }

}
