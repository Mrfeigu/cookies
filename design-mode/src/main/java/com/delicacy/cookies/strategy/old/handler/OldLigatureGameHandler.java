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
public class OldLigatureGameHandler extends AbstractGameHandler {


    public OldLigatureGameHandler() {
        super(GameEnum.LIGATURE.type, GameEnum.LIGATURE.desc);
    }

    @Override
    public void execute(){
        System.out.println("LigatureGameHandler");
    }

}
