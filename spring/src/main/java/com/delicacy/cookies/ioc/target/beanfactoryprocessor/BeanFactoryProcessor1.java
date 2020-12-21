package com.delicacy.cookies.ioc.target.beanfactoryprocessor;

import com.delicacy.cookies.ioc.target.BeanTargetService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/12/21
 */
@DependsOn("beanFactoryProcessor2")
@Service
public class BeanFactoryProcessor1 implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanTargetService bean = beanFactory.getBean(BeanTargetService.class);
        bean.setAge(100);
        bean.setName("beanFactoryProcessor1");

    }


    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.delicacy.cookies.ioc.target");
        BeanTargetService bean = ac.getBean(BeanTargetService.class);
        System.out.println(bean.getAge() + "   " + bean.getName());
        System.out.println("ending...");

    }


}
