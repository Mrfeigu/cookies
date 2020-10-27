package com.delicacy.cookies.factory.method;


import lombok.Builder;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Builder
public class BananaFactory implements FruitsFactory {

    @Override
    public BaseFruits create() {
        return new Banana(1);
    }

}
