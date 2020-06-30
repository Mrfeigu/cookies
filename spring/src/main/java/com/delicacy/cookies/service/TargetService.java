package com.delicacy.cookies.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 测试目标对象
 * @author linzhenghui
 * @date 2020/6/28
 */
@Service
public class TargetService {

    @Value("${target.username: 张三}")
    private String userName;
    @Value("${target.age: 1}")
    private Integer age;

    public String printlnMsg(){
        return userName + age;
    }

}
