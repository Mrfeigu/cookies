package com.delicacy.cookies.ioc.target;

import com.delicacy.cookies.ioc.target.beanfactoryprocessor.BeanPostProcessor1;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author linzhenghui
 * @date 2020/12/21
 */
@Data
@Slf4j
@Service
public class BeanTargetService {

    private String name = "BeanTargetService";
    private Integer age = 0;


    public static void main(String[] args) {

        ApplicationContext ac = new AnnotationConfigApplicationContext("com.delicacy.cookies.ioc.target");
        BeanTargetService bean = ac.getBean(BeanTargetService.class);
        System.out.println(bean.getAge() + "   " + bean.getName());
        System.out.println("ending...");

    }


}
