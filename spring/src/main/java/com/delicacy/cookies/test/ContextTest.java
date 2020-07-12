package com.delicacy.cookies.test;

import com.delicacy.cookies.aop.AopTargetService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * 加载环境
 * @author linzhenghui
 * @date 2020/6/28
 */
public class ContextTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        AopTargetService aopTargetService = ac.getBean(AopTargetService.class);
        String why = aopTargetService.printParam("why");
        System.out.println(why);
    }

}
