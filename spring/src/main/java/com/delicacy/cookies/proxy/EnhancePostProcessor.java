package com.delicacy.cookies.proxy;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author linzhenghui
 * @date 2020/12/24
 */
@Slf4j
@Component
public class EnhancePostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof ProxyTargetService) {

            try{
                return ProxyFactory.create(bean, ProxyTargetService.class);
            } catch (Exception ex) {
                log.error("EnhancePostProcessor#postProcessAfterInitialization error.", ex);
            }

        }

        return bean;
    }


}
