package com.delicacy.cookies.immutable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSetMultimap;

import java.util.HashMap;
import java.util.Map;

/**
 * 不可变的Map
 * 用来做定义数据
 *
 * http://ifeve.com/google-guava-immutablecollections/
 *
 * @author linzhenghui
 * @date 2020/10/10
 */
public class ImmutableMapUtils {


    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        ImmutableMap<String, String> immutableMap1 = ImmutableMap.copyOf(map);
        ImmutableMap<String, String> immutableMap2 = ImmutableMap.of();

        // 能够快速判断是否存在key或者value
        boolean w = immutableMap2.containsKey("w");
        boolean w1 = immutableMap2.containsValue("w");

        ImmutableSetMultimap<String, String> stringStringImmutableSetMultimap = immutableMap2.asMultimap();


    }




}
