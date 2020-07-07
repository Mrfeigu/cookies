package com.delicacy.cookies.service;


import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Service;

/**
 * 通过SmartInitializingSingleton这个接口去干预Bean属性，感觉已经很后了
 *
 * 测试目标对象
 * @author linzhenghui
 * @date 2020/6/28
 */
@Service
public class UserInfoService implements SmartInitializingSingleton {

    private String userName = "法外狂徒张三";

    public Object sayHello(){
        System.out.println("hello");
        return "hello";
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.userName = "暴走萝莉张三";
    }
}
