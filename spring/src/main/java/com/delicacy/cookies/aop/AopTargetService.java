package com.delicacy.cookies.aop;


import org.springframework.stereotype.Service;

import java.util.LinkedList;

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

    public void crash(){
        LinkedList<Byte[]> list = new LinkedList<>();
        while(1 == 1){
            Byte[] bytes = new Byte[1024*1024*5];
            list.addLast(bytes);
        }
    }

}
