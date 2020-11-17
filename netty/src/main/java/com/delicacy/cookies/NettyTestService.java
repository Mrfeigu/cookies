package com.delicacy.cookies;

import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/11/17
 */
@Service
public class NettyTestService {

    public Object test(String str) {
        System.out.println(str);
        return str;
    }

}
