package com.delicacy.cookies.postorocessor;

import com.delicacy.cookies.service.TargetService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Service;


/**
 * 验证BeanDefinitionRegistryPostProcessor
 * @author linzhenghui
 * @date 2020/06/28
 */
@Service
public class BeanDefinitionRegistryPostProcessorTestService implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("执行postProcessBeanDefinitionRegistry");
        BeanDefinition targetService = registry.getBeanDefinition("targetService");
        // 增加一个新的bean,类型与target相似
        GenericBeanDefinition targetService2 = new GenericBeanDefinition(targetService);
        registry.registerBeanDefinition("targetService2", targetService2);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 这个优先级没有postProcessBeanDefinitionRegistry高
        System.out.println("执行postProcessBeanFactory");
        TargetService targetService = (TargetService)beanFactory.getBean("targetService");
        TargetService targetService2 = (TargetService)beanFactory.getBean("targetService2");
        System.out.println(targetService.equals(targetService2));
        System.out.println(targetService == targetService2);
    }

}

