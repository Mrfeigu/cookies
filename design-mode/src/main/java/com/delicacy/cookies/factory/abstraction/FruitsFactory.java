package com.delicacy.cookies.factory.abstraction;


import com.delicacy.cookies.factory.method.BaseFruits;

/**
 * @author feigu
 * @date 2020/10/24
 */
public interface FruitsFactory {

    BaseFruits createApple();

    BaseFruits createBanana();


}
