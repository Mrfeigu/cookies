package com.delicacy.cookies.test;

import com.delicacy.cookies.aop.TargetService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * 加载环境
 * @author linzhenghui
 * @date 2020/6/28
 */
public class ContextTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        TargetService targetService = ac.getBean(TargetService.class);
        String why = targetService.printParam("why");
        System.out.println(why);
    }

}
