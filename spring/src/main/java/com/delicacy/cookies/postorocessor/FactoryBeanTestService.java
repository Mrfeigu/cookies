package com.delicacy.cookies.postorocessor;


import com.delicacy.cookies.service.UserInfoService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 验证FactoryBean，容器级别，丰富bean实例？
 *
 * @author linzhenghui
 * @date 2020/06/28
 */

@Service
public class FactoryBeanTestService implements FactoryBean<UserInfoService> {

    @Override
    public UserInfoService getObject() throws Exception {
        System.out.println("执行getObject");
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        System.out.println("执行getObjectType");
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
    }

}
