package com.delicacy.cookies.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author linzhenghui
 * @date 2020/12/23
 */
public class HelloProxy implements InvocationHandler {

    private Object hello;

    public HelloProxy(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("me logic");
        Object invoke = method.invoke(hello, args);
        System.out.println("me logic");
        return invoke;
    }

}
