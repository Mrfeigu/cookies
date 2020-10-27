package com.delicacy.cookies.factory.method;



/**
 * @author feigu
 * @date 2020/10/24
 */
public class test {

    public static void main(String[] args) {

        Banana banana = (Banana)BananaFactory.builder().build().create();
        Apple apple = (Apple)AppleFactory.builder().build().create();

        banana.sell();
        apple.sell();

        System.out.println("ending...");

    }


}
