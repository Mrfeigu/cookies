package com.delicacy.cookies.cycledepend;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/12/22
 */
@Service
public class BeanService2 {

    @Resource
    private BeanService1 beanService1;

    public void sayHello() {
        System.out.println("sayHello...");
        beanService1.sayHello();
    }

}
