package com.delicacy.cookies.strategy;

import com.delicacy.cookies.strategy.old.OldGameManager;
import com.delicacy.cookies.strategy.old.handler.GameHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Service("testGame")
public class test {

    @Autowired
    private OldGameManager oldGameManager;

    public Object test(int type){
        GameHandler gameTypeHandler = oldGameManager.getGameTypeHandler(type);
        if(gameTypeHandler != null){
            gameTypeHandler.execute();
        }
        return type;
    }




    public static void main(String[] args) {

        System.out.println("ending...");
    }


}
