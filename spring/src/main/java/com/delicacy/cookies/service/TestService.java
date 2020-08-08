package com.delicacy.cookies.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class TestService {

    public String userName = "张三";

    public Object testStr(String var) {
        return var;
    }



    public static void main(String[] args){
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
        acac.registerBean(TestService.class);
        acac.refresh();
        TestService bean = acac.getBean(TestService.class);
        System.out.println(bean.userName);
    }


}

