package com.delicacy.cookies.factory.abstraction;

import lombok.Data;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Data
public class AppleFirst extends Apple {

    AppleFirst(){
        super.setPrice(2);
    }

    public void sell(){
        System.out.println("AppleFirst:" + this.getPrice());
    };

}
