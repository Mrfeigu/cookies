package com.delicacy.cookies.strategy.now.annotation;

import com.delicacy.cookies.strategy.now.constant.GameEnum;
import java.lang.annotation.*;

/**
 * @author linzhenghui
 * @date 2020/11/5
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Game {

    /**
     * 游戏类型
     * @return
     */
    GameEnum gameType();

}
