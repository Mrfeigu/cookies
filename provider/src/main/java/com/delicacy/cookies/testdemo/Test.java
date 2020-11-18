package com.delicacy.cookies.testdemo;


import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author linzhenghui
 * @date 2020/7/3
 */

public class Test<T> {

    public static ArrayList<String> list = new ArrayList<>();

    private T a;


    public static void main(String[] args) throws NoSuchFieldException {
        Type type = Test.class.getDeclaredField("list").getGenericType();
        String typeName = type.getTypeName();


        System.out.println("ending...");
    }

}
