package com.delicacy.cookies.factory.method;

import lombok.Data;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Data
public abstract class BaseFruits {

    private int price;

    public abstract void sell();

}
