package com.delicacy.cookies.factory.abstraction;

import lombok.Data;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Data
public class AppleSecond extends Apple {

    AppleSecond(){
        super.setPrice(3);
    }

    public void sell(){
        System.out.println("AppleSecond:" + this.getPrice());
    };

}
