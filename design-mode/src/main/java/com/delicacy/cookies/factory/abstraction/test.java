package com.delicacy.cookies.factory.abstraction;


/**
 * @author feigu
 * @date 2020/10/24
 */
public class test {

    public static void main(String[] args) {

        FruitsFirstFactory.builder().build().createApple().sell();
        FruitsFirstFactory.builder().build().createBanana().sell();
        FruitsSecondFactory.builder().build().createApple().sell();
        FruitsSecondFactory.builder().build().createBanana().sell();
        System.out.println("ending...");

    }


}
