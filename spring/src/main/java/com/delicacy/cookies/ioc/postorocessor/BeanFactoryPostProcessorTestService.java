package com.delicacy.cookies.ioc.postorocessor;

import com.delicacy.cookies.ioc.service.TargetService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

/**
 * 验证BeanFactoryPostProcessor
 * @author linzhenghui
 * @date 2020/7/1
 */

@Service
public class BeanFactoryPostProcessorTestService implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("执行postProcessBeanFactory");
        TargetService targetService = (TargetService)beanFactory.getBean("targetService");
        TargetService targetService3 = new TargetService();
        targetService3.setAge(28);
        targetService3.setUserName("法外狂徒张三");
        beanFactory.registerSingleton("targetService3", targetService3);
    }

}
