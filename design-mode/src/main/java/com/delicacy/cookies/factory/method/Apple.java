package com.delicacy.cookies.factory.method;


/**
 * @author feigu
 * @date 2020/10/24
 */
public class Apple extends BaseFruits {

    Apple(int price){
        super.setPrice(price);
    }

    @Override
    public void sell() {
        System.out.println("apple:" + getPrice());
    }


}
