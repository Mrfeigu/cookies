package com.delicacy.cookies.ioc.test;

import com.delicacy.cookies.ioc.service.UserInfoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * 加载环境
 * @author linzhenghui
 * @date 2020/6/28
 */
public class ContextTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserInfoService targetService = ac.getBean(UserInfoService.class);
        System.out.println(targetService.sayHello());
    }

}
