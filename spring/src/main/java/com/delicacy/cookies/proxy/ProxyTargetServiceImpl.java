package com.delicacy.cookies.proxy;

import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/12/24
 */
@Service
public class ProxyTargetServiceImpl implements ProxyTargetService {

    @Override
    public void sayHi() {
        System.out.println("hi");
    }

    @Override
    public void sayHello() {
        System.out.println("hello");
    }

}
