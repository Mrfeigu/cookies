package com.delicacy.cookies.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linzhenghui
 * @date 2021/02/02
 */
public class AtomicUtil {



    public static void main(String[] args) {

        AtomicInteger ai = new AtomicInteger(0);
        System.out.println(ai.get());

        ai.accumulateAndGet(10, (x, y) -> x - y);
        System.out.println(ai.get());

    }



}
