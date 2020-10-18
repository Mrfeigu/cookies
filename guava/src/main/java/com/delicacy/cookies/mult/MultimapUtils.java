package com.delicacy.cookies.mult;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.*;

/**
 * http://ifeve.com/google-guava-newcollectiontypes/
 *
 * Multimap
 * Multimap.get(key)总是返回非null、但是可能空的集合。
 *
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class MultimapUtils {

    /**
     * ArrayListMultimap， HashMultimap， LinkedListMultimap， LinkedHashMultimap， TreeMultimap， ImmutableListMultimap， ImmutableSetMultimap
     */
    private static Multimap<String, String> multimap = HashMultimap.create();

    public static void main(String[] args) {

        multimap.put("eason", "今天等我来");
        multimap.put("eason", "好歌献给你");
        multimap.put("eason", "落花流水");
        multimap.put("eason", "富士山下");
        multimap.put("eason", "k歌之王");
        multimap.put("eason", "好久不见");
        multimap.put("eason", "阿牛");

        multimap.put("jay", "晴天");
        multimap.put("jay", "七里香");
        multimap.put("jay", "听妈妈的话");
        multimap.put("jay", "以父之名");

        multimap.put("林宥嘉", "说谎");

        // 重复添加，仅存一个，不会抛出异常
        multimap.put("林宥嘉", "你是我的眼");
        multimap.put("林宥嘉", "你是我的眼");

        // key, value 都可以为null
        multimap.put(null, "");
        multimap.put(null, null);


        Collection<String> eason = multimap.get("eason");
        List<String> easonList = new ArrayList<>(eason);

        int size = multimap.size();

        Set<String> strings = multimap.keySet();
        Collection<String> values = multimap.values();

        Map<String, Collection<String>> stringCollectionMap = multimap.asMap();

        boolean jay = multimap.containsKey("jay");
        boolean b = multimap.containsEntry("jay", "晴天");
        boolean c = multimap.containsValue("阿牛");

        multimap.replaceValues("林宥嘉", Lists.newArrayList("说谎","残酷月光", "浪费", "成全"));

        boolean remove = multimap.remove("林宥嘉", "说谎");
        Collection<String> re = multimap.removeAll("林宥嘉");

        multimap.clear();

        System.out.println("ending...");
    }


}
