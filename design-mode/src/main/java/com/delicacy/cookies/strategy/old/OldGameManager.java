package com.delicacy.cookies.strategy.old;

import com.delicacy.cookies.strategy.old.handler.GameHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author linzhenghui
 * @date 2020/11/6
 */
@Component
public class OldGameManager {

    private static OldGameManager INSTANCE;

    private final ConcurrentHashMap<Integer, GameHandler> gameTypeHandlerMap;

    public OldGameManager() {

        this.gameTypeHandlerMap = new ConcurrentHashMap<>();
        if (INSTANCE != null) {
            throw new RuntimeException("[GameManager] can only instance once");
        }
        INSTANCE = this;
    }

    public static OldGameManager getInstance() {
        return INSTANCE;
    }


    public void registerHandler(GameHandler handler) {
        int command = handler.getGameType();

        GameHandler old = gameTypeHandlerMap.get(command);
        if (old != null) {
        }

        gameTypeHandlerMap.put(command, handler);
    }

    public GameHandler getGameTypeHandler(int command) {
        return gameTypeHandlerMap.get(command);
    }





}
