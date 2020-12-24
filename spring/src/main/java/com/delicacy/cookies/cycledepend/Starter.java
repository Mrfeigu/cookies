package com.delicacy.cookies.cycledepend;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author linzhenghui
 * @date 2020/12/22
 */
public class Starter {


    public static void main(String[] args) {

        ApplicationContext ac = new AnnotationConfigApplicationContext("com.delicacy.cookies.cycledepend");
        BeanService2 bean = ac.getBean(BeanService2.class);
        // bean.sayHello();
        System.out.println("ending...");

    }


}
