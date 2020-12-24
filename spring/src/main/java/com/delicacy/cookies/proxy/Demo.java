package com.delicacy.cookies.proxy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author linzhenghui
 * @date 2020/12/24
 */
public class Demo {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.delicacy.cookies.proxy");
        ProxyTargetService bean = ac.getBean(ProxyTargetService.class);

        bean.sayHi();
        bean.sayHello();

    }


}
