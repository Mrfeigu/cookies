package com.delicacy.cookies.util;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author linzhenghui
 * @date 2021/2/9
 */
public class MethodMapUtil {

    /** 一进一出*/
    private Map<String, Function<Integer,String>> functionMap = new HashMap<>();
    /** 一进零出*/
    private Map<String, Consumer<Integer>> consumerMap = new HashMap<>();
    /** 零进零出*/
    private Map<String, Supplier<Integer>> supplierMap = new HashMap<>();
    /** 一参一布尔*/
    private Map<String, Predicate<Integer>> predicateMap = new HashMap<>();





    public static void main(String[] args) {

    }


}
