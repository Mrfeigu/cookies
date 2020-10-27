package com.delicacy.cookies.factory.method;


/**
 * @author feigu
 * @date 2020/10/24
 */
public class Banana extends BaseFruits {

    Banana(int price){
        super.setPrice(price);
    }

    @Override
    public void sell() {
        System.out.println("banana:" + getPrice());
    }


}
