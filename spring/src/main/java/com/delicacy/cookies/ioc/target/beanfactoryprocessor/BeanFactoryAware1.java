package com.delicacy.cookies.ioc.target.beanfactoryprocessor;

import com.delicacy.cookies.ioc.target.BeanTargetService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/12/22
 */
@Service
public class BeanFactoryAware1 implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        System.out.println("BeanFactoryAware1#setBeanFactory");
        BeanTargetService bean = beanFactory.getBean(BeanTargetService.class);
        bean.setAge(190);
        bean.setName("BeanFactoryAware1");

    }

}
