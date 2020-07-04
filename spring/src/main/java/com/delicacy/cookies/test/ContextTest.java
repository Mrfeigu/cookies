package com.delicacy.cookies.test;

import com.delicacy.cookies.factorybean.FactoryBeanTestService;
import com.delicacy.cookies.service.UserInfoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * 加载环境
 * @author linzhenghui
 * @date 2020/6/28
 */
public class ContextTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserInfoService userInfoService1 = ac.getBean(UserInfoService.class);
        UserInfoService userInfoService2 = (UserInfoService)ac.getBean("factoryBeanTestService");
        FactoryBeanTestService factoryBeanTestService = (FactoryBeanTestService)ac.getBean("&factoryBeanTestService");
        System.out.println(userInfoService1 == userInfoService2);
        System.out.println("ending...");
    }

}
