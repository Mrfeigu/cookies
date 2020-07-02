package com.delicacy.cookies.postorocessor;


import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Service;



/**
 * 验证InstantiationAwareBeanPostProcessor，漏了targetService， 实际上继承了BeanPostProcessor，丰富了方法
 * 能够在实例化Bean的前后进行方法的拦截，但是会漏Bean
 * @author linzhenghui
 * @date 2020/06/28
 */


//@Service
public class InstantiationAwareBeanPostProcessorTestService implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("执行postProcessBeforeInstantiation");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("执行postProcessAfterInstantiation");
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("执行postProcessProperties");
        return null;
    }

}
