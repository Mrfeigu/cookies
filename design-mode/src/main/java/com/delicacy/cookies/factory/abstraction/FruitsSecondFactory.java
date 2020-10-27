package com.delicacy.cookies.factory.abstraction;

import com.delicacy.cookies.factory.method.BaseFruits;
import lombok.Builder;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Builder
public class FruitsSecondFactory implements FruitsFactory {


    @Override
    public BaseFruits createApple() {
        return new AppleSecond();
    }

    @Override
    public BaseFruits createBanana() {
        return new BananaSecond();
    }


}
