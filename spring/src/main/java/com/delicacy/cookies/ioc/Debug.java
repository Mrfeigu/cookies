package com.delicacy.cookies.ioc;

import com.delicacy.cookies.ioc.service.ChocolateService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author linzhenghui
 * @date 2021/1/16
 */
public class Debug {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext("com.delicacy.cookies.ioc");
        ChocolateService bean = ac.getBean(ChocolateService.class);
        System.out.println("ending...");
    }


}
