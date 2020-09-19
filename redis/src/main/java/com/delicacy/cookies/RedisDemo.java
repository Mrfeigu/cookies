package com.delicacy.cookies;

import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/9/15
 */
@Service
public class RedisDemo {

    public Object test(){
        System.out.println("早上好");
        return "早上好";
    }

}
