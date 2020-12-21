package com.delicacy.cookies.ioc.target.beanfactoryprocessor;

import com.delicacy.cookies.ioc.target.BeanTargetService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/12/21
 */
@Service
public class BeanFactoryProcessor2 implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanTargetService bean = beanFactory.getBean(BeanTargetService.class);
        bean.setAge(200);
        bean.setName("beanFactoryProcessor2");

    }
}
