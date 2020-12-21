package com.delicacy.cookies.myImport;

import lombok.Data;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 这个方式真的能带出
 * @author linzhenghui
 * @date 2020/12/16
 */
@Data
public class ColaImportService3 implements ImportSelector {

    private String colaName = "初始名";
    private Integer colaType = 0;

    private String beanName;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 不能加载类本身自由注册类 这里是com.delicacy.cookies.myImport.ColaImportService3
        // 同理也可以集成商ImportBeanDefinitionRegistrar
        return new String[]{"com.delicacy.cookies.myImport.ColaImportService2"};
    }
}
