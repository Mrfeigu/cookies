package com.delicacy.cookies.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 拿到上下文容器applicationContext，能发布事件，能获取bean对象，不是Factory对象不能拿到BeanDifinition，能拿到环境信息
 * 执行时机在 refresh方法中的finishBeanFactoryInitialization(beanFactory)
 * @author linzhenghui
 * @date 2020/7/1
 */
@Service
public class ApplicationContextAwareTestService implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("执行setApplicationContext");
        Environment environment = applicationContext.getEnvironment();
    }

}
