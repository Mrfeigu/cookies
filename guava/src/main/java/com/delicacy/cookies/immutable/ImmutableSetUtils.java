package com.delicacy.cookies.immutable;


import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Set;

/**
 * 不可变的set
 * 用来做定义数据
 *
 * http://ifeve.com/google-guava-immutablecollections/
 *
 * @author linzhenghui
 * @date 2020/10/10
 */
public class ImmutableSetUtils {

    public static void setUtilDemo(){
        Set<String> set = new HashSet<>();
        set.add("锤石");
        set.add("蒙多");
        set.add("阿卡丽");
        set.add("劫");
        ImmutableSet<String> immutableSet1 = ImmutableSet.copyOf(set);
        ImmutableSet<String> immutableSet2 = ImmutableSet.of("崔斯特");

    }


    public static void main(String[] args) {

    }



}
