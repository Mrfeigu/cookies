package com.delicacy.cookies.strategy.old.handler;

import com.delicacy.cookies.strategy.old.OldGameManager;

/**
 * @author linzhenghui
 * @date 2020/11/6
 */

public class AbstractGameHandler implements GameHandler {


    private  int gameType;

    private String gameName;

    public AbstractGameHandler(int gameType, String gameName) {
        this.gameType = gameType;
        this.gameName = gameName;
        OldGameManager.getInstance().registerHandler(this);
    }

    @Override
    public int getGameType() {
        return this.gameType;
    }

    @Override
    public String getGameName() {
        return this.getGameName();
    }

    @Override
    public void execute() {
        // 下游处理类执行
    }

}
