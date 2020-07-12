package com.delicacy.cookies.aop;


import org.springframework.stereotype.Service;

/**
 * aop目标测试类
 * @author linzhenghui
 * @date 2020/7/12
 */

@Service
public class AopTargetService {

    @TargetAnnotation(value = true)
    public String printParam(String var){
        return var;
    }

}
