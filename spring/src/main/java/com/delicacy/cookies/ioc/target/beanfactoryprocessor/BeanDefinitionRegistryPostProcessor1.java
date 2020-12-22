package com.delicacy.cookies.ioc.target.beanfactoryprocessor;

import com.delicacy.cookies.ioc.target.BeanTargetService;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/12/22
 */
@Service
public class BeanDefinitionRegistryPostProcessor1 implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        BeanDefinition bd = registry.getBeanDefinition("beanTargetService");
        MutablePropertyValues propertyValues = bd.getPropertyValues();
        propertyValues.addPropertyValue("name", "postProcessBeanDefinitionRegistry");
        propertyValues.addPropertyValue("age", 110);

        /* 在BeanDefinition上定义了注册没有的属性，最后转换成Bean的时候，会BeanCreationException
        propertyValues.addPropertyValue("cad", "wdnmdndw"); */
        System.out.println("BeanDefinitionRegistryPostProcessor1#postProcessBeanDefinitionRegistry");

    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

         BeanTargetService bean = beanFactory.getBean(BeanTargetService.class);
         bean.setName("postProcessBeanFactory");
         bean.setAge(112);

    }

}
