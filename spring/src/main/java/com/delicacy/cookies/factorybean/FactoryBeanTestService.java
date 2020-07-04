package com.delicacy.cookies.factorybean;


import com.delicacy.cookies.service.UserInfoService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

/**
 * 验证FactoryBean，容器级别，丰富bean实例?
 *
 * 结论：事实上容器里面的叫factoryBeanTestService的bean其实是UserInfoService，返回的new Bean
 * FactoryBeanTestService这个生成的Bean被命名为 &factoryBeanTestService
 * 发生时机
 *
 * @author linzhenghui
 * @date 2020/06/28
 */

@Service
public class FactoryBeanTestService implements FactoryBean<UserInfoService> {

    @Override
    public UserInfoService getObject() throws Exception {
        System.out.println("执行getObject");
        return new UserInfoService();
    }

    @Override
    public Class<?> getObjectType() {
        return UserInfoService.class;
    }


}
