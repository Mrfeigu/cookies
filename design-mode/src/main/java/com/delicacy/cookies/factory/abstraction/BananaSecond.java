package com.delicacy.cookies.factory.abstraction;

import lombok.Data;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Data
public class BananaSecond extends Banana {

    BananaSecond(){
        super.setPrice(1);
    }

    @Override
    public void sell() {
        System.out.println("BananaFirst:" + this.getPrice());
    }
}
