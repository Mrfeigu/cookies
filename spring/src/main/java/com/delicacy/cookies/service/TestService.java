package com.delicacy.cookies.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;


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

        try {
            Field userName = bean.getClass().getDeclaredField("userName");
            userName.setAccessible(true);
            userName.set(bean, "wdnmd");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println(bean.userName);
    }


}

