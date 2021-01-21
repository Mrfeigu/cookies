package com.delicacy.cookies.util;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @author linzhenghui
 * @date 2021/1/21
 */
public class CollectionSwapUtil {


    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);
        Collections.swap(list, 0, list.size() - 1);
        for (Integer integer : list) {
            System.out.print(integer + "   ");
        }

    }


}
