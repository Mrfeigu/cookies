package com.delicacy.cookies.strategy.now;

import com.delicacy.cookies.strategy.now.handle.AbstractGameHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import com.delicacy.cookies.strategy.now.annotation.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author linzhenghui
 * @date 2020/11/5
 */
@Slf4j
@Component
public class GameManager implements ApplicationContextAware {


    private ConcurrentHashMap<Integer, AbstractGameHandler> gameMap = new ConcurrentHashMap<>(64);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 模式存储到map
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Game.class);
        beans.forEach((k, v)->{
            int key = v.getClass().getAnnotation(Game.class).gameType().type;
            gameMap.put(key, (AbstractGameHandler)v);
        });
    }

    /**
     * 执行方法
     * @param type
     */
    public void exe(int type){
        AbstractGameHandler handler = gameMap.get(type);
        if(null == handler){
            return;
        }
        handler.execute();
    }


}
