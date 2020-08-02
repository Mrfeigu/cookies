package com.lc.exercise.mid;

import java.util.*;

/**
 * 字母异位词分组
 * 淦，超时
 * @author linzhenghui
 * @date 2020/07/31
 */
public class exercise25 {

    private Map<String, List<String>> map = new HashMap<>();

    public List<List<String>> groupAnagrams(String[] strs) {
        A:for (String str : strs) {
            for (String s : map.keySet()) {
                if (judge(s, str)) {
                    map.get(s).add(str);
                    continue A;
                }
            }
            List<String> re = new ArrayList<>();
            re.add(str);
            map.put(str, re);
        }
        return new ArrayList<>(map.values());
    }


    private boolean judge(String source, String target){
        String sourceStr = getSortStr(source);
        String targetStr = getSortStr(target);
        return sourceStr.equals(targetStr);
    }

    static String getSortStr(String source){
        List<Character> list = new LinkedList<>();
        for (int i = 0; i < source.length(); i++) {
            list.add(source.charAt(i));
        }
        list.sort(Character::compareTo);
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : list) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    /**
     * 官方题解
     * 想法一致
     * 排序： Arrays.sort(ca);
     * 字符串转字符数组：char[] ca = s.toCharArray();
     */
    public List<List<String>> _groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    /**
     * 官方题解， 类似hash表
     * @param strs
     * @return
     */
    public List<List<String>> _groupAnagrams2(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }


    public static void main(String[] args){
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = new exercise25().groupAnagrams(str);
        System.out.println("ending...");

    }

}
