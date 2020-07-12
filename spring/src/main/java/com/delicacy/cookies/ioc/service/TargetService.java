package com.delicacy.cookies.ioc.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 测试目标对象
 * @author linzhenghui
 * @date 2020/6/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class TargetService {

    private String userName = "张三";
    private Integer age = 11;

    public String printlnMsg(){
        return userName + age;
    }

}
