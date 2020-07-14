package com.delicacy.cookies.testdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linzhenghui
 * @date 2020/7/3
 */

public class Test {

    public static void main(String[] args) {
        System.out.println("qt-teacher".toUpperCase());
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);
        List<Integer> integers = list.subList(1, 2);
        System.out.println("ending...");
    }

}
