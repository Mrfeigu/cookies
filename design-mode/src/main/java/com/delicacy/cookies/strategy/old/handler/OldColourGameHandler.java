package com.delicacy.cookies.strategy.old.handler;

import com.delicacy.cookies.strategy.old.constant.GameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author linzhenghui
 * @date 2020/11/5
 */
@Slf4j
@Component
public class OldColourGameHandler extends AbstractGameHandler {


    public OldColourGameHandler() {
        super(GameEnum.COLOUR.type, GameEnum.COLOUR.desc);
    }

    @Override
    public void execute(){
        System.out.println("ColourGameHandler");
    }

}
