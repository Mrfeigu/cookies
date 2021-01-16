package com.delicacy.cookies.ioc.postorocessor;

import com.delicacy.cookies.ioc.service.ChocolateService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 干预Bean的初始化，在populateBean上触发
 *
 *
 *
 * @author linzhenghui
 * @date 2021/1/16
 */
@Component
public class DemoInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {


    /**
     * 控制属性注入，返回true是正常流程，返回false，对Bean的属性都不进行依赖注入。
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(bean instanceof ChocolateService) {
            return false;
        }
        return true;
    }


}
