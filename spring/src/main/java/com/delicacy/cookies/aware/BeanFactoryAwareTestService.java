package com.delicacy.cookies.aware;

import com.delicacy.cookies.service.TargetService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

/**
 * 可以通过BeanFactory拿到bean，方法相对较少，已经不能拿到BeanDefinition对象
 * 执行时机在 refresh方法中的finishBeanFactoryInitialization(beanFactory)
 * @author linzhenhui
 * @date 2020/7/1
 */
@Service
public class BeanFactoryAwareTestService implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        TargetService targetService = (TargetService)beanFactory.getBean("targetService3");
        targetService.setUserName("雷霆咆哮张三");
    }

}
