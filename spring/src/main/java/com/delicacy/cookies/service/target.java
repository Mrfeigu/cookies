package com.delicacy.cookies.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 测试目标对象
 * @author linzhenghui
 * @date 2020/6/28
 */
@Component
public class target {

    @Value("${target.username}")
    private String userName;
    @Value("${target.age}")
    private Integer age;

    public String printlnMsg(){
        return userName + age;
    }

}
