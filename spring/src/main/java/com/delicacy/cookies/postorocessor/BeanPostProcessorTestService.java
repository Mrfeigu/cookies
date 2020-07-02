package com.delicacy.cookies.postorocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * 验证InstantiationAwareBeanPostProcessor， 实际上继承了BeanPostProcessor，丰富了方法
 * 能够在实例化Bean的前后进行方法的拦截
 * @author linzhenghui
 * @date 2020/06/28
 */

@Service
public class BeanPostProcessorTestService implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行postProcessBeforeInitialization");
        System.out.println(beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行postProcessAfterInitialization");
        return bean;
    }

}
