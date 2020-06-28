package com.delicacy.cookies.BeanDefinitionRegistryPostProcessor;

import com.delicacy.cookies.service.target;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;


/**
 * 验证BeanDefinitionRegistryPostProcessor
 * @author linzhenghui
 * @date 2020/06/28
 */
@DependsOn({"target"})
@Service
public class TestService implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware, InitializingBean {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("执行postProcessBeanDefinitionRegistry");
        BeanDefinition target = registry.getBeanDefinition("target");
        // 获取bean名称
        BeanDefinition target2 = new GenericBeanDefinition(target);
        System.out.println("注册target2这个bean");
        registry.registerBeanDefinition("target2", target2);
        System.out.println("完成target2的注册");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 这个优先级没有postProcessBeanDefinitionRegistry高
        System.out.println("执行postProcessBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("执行setApplicationContext");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        String[] beanNamesForType = applicationContext.getBeanNamesForType(target.class);
        for (String s : beanNamesForType) {
            target target = (target)applicationContext.getBean(s);
            target.printlnMsg();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行afterPropertiesSet");
        System.out.println("testService构建完成");
    }



}

