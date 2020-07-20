package com.delicacy.cookies.testdemo;

import com.delicacy.cookies.util.MemoryPagingUtil;

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
        List<Integer> paging = MemoryPagingUtil.paging(list, 0, 1);
        System.out.println("ending...");
    }

}
