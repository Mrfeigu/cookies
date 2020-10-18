package com.delicacy.cookies.immutable;

import com.google.common.collect.ImmutableList;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * 不可变List
 * @author linzhenghui
 * @date 2020/10/18
 */

public class ImmutableListUtils {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        ImmutableList immutableList = ImmutableList.copyOf(list);

    }


}
