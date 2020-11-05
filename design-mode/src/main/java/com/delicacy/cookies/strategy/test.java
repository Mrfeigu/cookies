package com.delicacy.cookies.strategy;

import com.delicacy.cookies.strategy.now.GameManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author feigu
 * @date 2020/10/24
 */
@Service("testGame")
public class test {

    @Resource
    private GameManager gameManager;

    public Object test(int type){
        gameManager.exe(type);
        return type;
    }




    public static void main(String[] args) {

        System.out.println("ending...");
    }


}
