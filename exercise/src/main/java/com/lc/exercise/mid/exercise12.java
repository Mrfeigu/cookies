package com.lc.exercise.mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class exercise12 {


    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> list = map.computeIfAbsent(groupSizes[i], k -> new ArrayList<Integer>());
            if(list.size() >= groupSizes[i]){
                result.add(list);
                map.remove(groupSizes[i]);
                map.put(groupSizes[i], new ArrayList<Integer>());
                map.get(groupSizes[i]).add(i);
            }else {
                list.add(i);
            }
        }
        map.forEach((k,v)->{
            result.add(v);
        });

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1};
        groupThePeople(arr);
    }

}
