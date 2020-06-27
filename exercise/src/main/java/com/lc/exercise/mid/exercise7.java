package com.lc.exercise.mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class exercise7 {



    public static String frequencySort(String s) {
        if(s == null || s.length() < 1) return "";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet()).stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        StringBuilder result = new StringBuilder();
        for (int i = list.size()-1; i >= 0 ; i--) {
            Map.Entry<Character, Integer> m = list.get(i);
            int index = m.getValue();
            while(index > 0){
                result.append(m.getKey());
                index--;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {

        System.out.println(frequencySort("tree"));

    }

}
