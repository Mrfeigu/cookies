package com.delicacy.cookies.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 测试目标对象
 * @author linzhenghui
 * @date 2020/6/28
 */
@Data
@Service
public class TargetService {

    private String userName = "张三";
    private Integer age = 11;

    public String printlnMsg(){
        return userName + age;
    }

}
