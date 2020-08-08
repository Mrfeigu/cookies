package com.lc.exercise.mid;

import java.util.LinkedList;

public class exercise8 {

    public static int minAddToMakeValid(String S) {
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            Character last = null;
            if(!list.isEmpty()){
                last = list.getLast();
            }
            if(last != null){
                if(last == '(' && c == ')'){
                    list.removeLast();
                }else{
                    list.add(c);
                }
            }else{
                list.add(c);
            }
        }
        return list.size();
    }



    public static void main(String[] args) {
        System.out.println(minAddToMakeValid(""));
    }

}
