package com.delicacy.cookies.testdemo;


import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author linzhenghui
 * @date 2020/7/3
 */

public class Test<T> {

    public static ArrayList<String> list = new ArrayList<>();

    private T a;

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    public static void main(String[] args) throws NoSuchFieldException {
        Type type = Test.class.getDeclaredField("list").getGenericType();
        String typeName = type.getTypeName();

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(SIMPLE_DATE_FORMAT.format(new Date()));
        System.out.println("ending...");
    }

}
