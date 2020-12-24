package com.delicacy.cookies.cycledepend;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 构造器的循环依赖，spring是没办法解决的，同理非单例模式下的Setter循环依赖，spring也是没法处理的
 *
 *
 * @author linzhenghui
 * @date 2020/12/22
 */
@Service
public class BeanService1 {

    @Resource
    private BeanService2 beanService2;


    public void sayHello() {
        System.out.println("sayHello...");
        beanService2.sayHello();
    }




}
