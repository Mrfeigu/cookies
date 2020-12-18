package com.delicacy.cookies.myImport;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Service;

/**
 * 这个方式真的能带出
 * @author linzhenghui
 * @date 2020/12/16
 */
@Data
public class ColaImportService2 implements ImportBeanDefinitionRegistrar, InitializingBean {

    private String colaName = "初始名";
    private Integer colaType = 0;

    private String beanName;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 这里可以自定义bean注册
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(ColaImportService2.class).getBeanDefinition();

        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue("colaName", "改个名");
        propertyValues.addPropertyValue("colaType", 1);

        registry.registerBeanDefinition("colaImportService2", beanDefinition);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 成功修改Bean属性
        System.out.println(this.getColaName());
        System.out.println(this.getColaType());

    }

}
