package com.delicacy.cookies.ioc.target.beanfactoryprocessor;

import com.delicacy.cookies.ioc.target.BeanTargetService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 作用：
 * BeanPostProcessor 发生在Bean已经创建之后，也就是调用了getBean之后的applyBeanPostProcessorsBeforeInitialization会增强到属性
 *
 * BeanPostProcessor失效的原因：
 * BeanPostProcessor也会可能失效，也就是自定义的BeanPostProcessor注册到BeanPostProcessorList时候，想要增强的Bean已经被初始化了
 * 这种情况就好像被BeanDefinitionRegistryPostProcessor预先加载 或者 被BeanFactoryProcessor预先加载，都会丢失Bean在BeanPostProcessor列表中的增强
 * 也就是在自定义的BeanPostProcessor注册上的时候，已经提前初始化Bean了
 *
 * 发现问题的过程：
 * 1. 开始认为自定义的BeanPostProcessor1没有添加到列表，直接结束，从registerBeanPOSTProcessor方法上，发现了实际上是有注册的
 * 2. 后来注意到了BeanPostProcessor1注册时，BeanTargetService这个Bean已经被初始化了
 * 3. 后面屏蔽 BeanDefinitionRegistryPostProcessor1， BeanFactoryProcessor1， BeanFactoryProcessor2 的getBean后，能正常使用BeanPostProcessor
 *
 *
 * @author linzhenghui
 * @date 2020/12/22
 */
@Component
public class BeanPostProcessor1 implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof BeanTargetService) {
            System.out.println("BeanPostProcessor1#Before beanName:" + beanName);
            BeanTargetService bts = (BeanTargetService) bean;
            bts.setName("postProcessBeforeInitialization");
            bts.setAge(114);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof BeanTargetService) {
            System.out.println("BeanPostProcessor1#After beanName:" + beanName);
            BeanTargetService bts = (BeanTargetService) bean;
            bts.setName("postProcessAfterInitialization");
            bts.setAge(115);
        }
        return bean;

    }

}
