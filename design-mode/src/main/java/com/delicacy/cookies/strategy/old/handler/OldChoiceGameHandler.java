package com.delicacy.cookies.strategy.old.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.delicacy.cookies.strategy.old.constant.GameEnum;

/**
 * @author linzhenghui
 * @date 2020/11/5
 */
@Slf4j
@Component
public class OldChoiceGameHandler extends AbstractGameHandler {


    public OldChoiceGameHandler() {
        super(GameEnum.CHOICE.type, GameEnum.CHOICE.desc);
    }

    @Override
    public void execute(){
        System.out.println("ChoiceGameHandler");
    }

}
