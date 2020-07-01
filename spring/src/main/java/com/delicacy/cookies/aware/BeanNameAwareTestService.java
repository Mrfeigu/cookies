package com.delicacy.cookies.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * 感觉没啥用。只是获取容器中bean的名称，执行时机在 refresh方法中的finishBeanFactoryInitialization(beanFactory);
 * 验证BeanNameAware
 * @author linzhenghui
 * @date 2020/7/1
 */
@Service
public class BeanNameAwareTestService implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }
}
