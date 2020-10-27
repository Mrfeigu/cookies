package com.delicacy.cookies.factory.abstraction;

import lombok.Data;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Data
public class BananaFirst extends Banana {

    BananaFirst(){
        super.setPrice(0);
    }

    @Override
    public void sell() {
        System.out.println("BananaFirst:" + this.getPrice());
    }
}
