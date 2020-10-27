package com.delicacy.cookies.factory.abstraction;

import com.delicacy.cookies.factory.method.BaseFruits;
import lombok.Data;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Data
public abstract class Banana extends BaseFruits {

    private int price;

    public abstract void sell();

}
