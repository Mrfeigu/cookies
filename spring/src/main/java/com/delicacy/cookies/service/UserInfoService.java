package com.delicacy.cookies.service;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * 测试目标对象
 * @author linzhenghui
 * @date 2020/6/28
 */
//@Service
public class UserInfoService implements InitializingBean {

    public Object sayHello(){
        System.out.println("hello");
        return "hello";
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("hello");
    }
}
