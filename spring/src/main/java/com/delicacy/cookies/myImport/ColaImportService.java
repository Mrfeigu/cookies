package com.delicacy.cookies.myImport;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/12/16
 */
@Data
@Service
public class ColaImportService implements ImportBeanDefinitionRegistrar, InitializingBean, BeanPostProcessor {

    private String colaName = "初始名";
    private Integer colaType = 0;

    private String beanName;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 这个加载方法有问题，并没有加载Bean
        BeanDefinition beanDefinition = registry.getBeanDefinition("colaImportService");

        // 对当前service属性进行修改
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue("colaName", "haoqilai");
        propertyValues.addPropertyValue("colaType", 1);

        registry.registerBeanDefinition("colaImportService", beanDefinition);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 成功修改Bean属性
        System.out.println(this.getColaName());
        System.out.println(this.getColaType());

    }

    /**
     * 一般加载是当前，但是这个类是从ImportDemo类上import进来的，所以当前Bean是ImportDemo
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof ColaImportService) {
            String colaName = ((ColaImportService) bean).getColaName();
            Integer colaType = ((ColaImportService) bean).getColaType();
            System.out.println("postProcessBeforeInitialization-colaName:" + colaName);
            System.out.println("postProcessBeforeInitialization-colaType:" + colaType);
        }

        return bean;
    }


}
