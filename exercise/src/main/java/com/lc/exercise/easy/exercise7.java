package com.lc.exercise.easy;

import java.util.LinkedList;

/**
 * 最长公共前缀
 *
 * @author linzhenghui
 * @date 2020/10/20
 */
public class exercise7 {

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length < 1) return "";

        LinkedList<Character> resList = new LinkedList<>();

        int index = 0;
        boolean flag = false;
        A:while(true){
            for (int i = 0; i < strs.length; i++) {
                try {
                    char c = strs[i].charAt(index);
                    if(i == 0){
                        resList.add(c);
                    }else{
                        if(!resList.getLast().equals(c)){
                            break A;
                        }
                    }
                }catch (Exception ex){
                    if(i == 0){
                        flag = true;
                    }
                    break A;
                }
            }
            index++;
        }

        int size = flag ?  resList.size() : resList.size() - 1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(resList.get(i));
        }
        return sb.toString();

    }

    public static void main(String[] args){
        String[] str = {"a"};
        String s = longestCommonPrefix(str);
        System.out.println(s);
        System.out.println("ending...");
    }

}
