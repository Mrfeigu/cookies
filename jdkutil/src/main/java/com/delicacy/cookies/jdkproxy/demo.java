package com.delicacy.cookies.jdkproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author linzhenghui
 * @date 2020/12/23
 */
public class demo {


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> proxyClass = Proxy.getProxyClass(Hello.class.getClassLoader(), Hello.class);
        Hello hello = (Hello)proxyClass.getConstructor(InvocationHandler.class).newInstance(new HelloProxy(new HelloImpl()));
        hello.sayHello("s");
    }

}
