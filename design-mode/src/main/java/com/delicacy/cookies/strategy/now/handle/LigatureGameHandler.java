package com.delicacy.cookies.strategy.now.handle;

import com.delicacy.cookies.strategy.now.annotation.Game;
import com.delicacy.cookies.strategy.now.constant.GameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author linzhenghui
 * @date 2020/11/5
 */
@Slf4j
@Component
@Game(gameType = GameEnum.LIGATURE)
public class LigatureGameHandler extends AbstractGameHandler {
    @Override
    public void execute() {

        System.out.println("连线执行方法");

    }
}
