package com.delicacy.cookies.ioc.target.beanfactoryprocessor;

import com.delicacy.cookies.ioc.target.BeanTargetService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author linzhenghui
 * @date 2020/12/22
 */
@Component
public class ApplicationContextAware1 implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanTargetService bean = applicationContext.getBean(BeanTargetService.class);
        bean.setName("ApplicationContextAware1");
        bean.setAge(116);
        System.out.println("ApplicationContextAware1#setApplicationContext");
    }

}
