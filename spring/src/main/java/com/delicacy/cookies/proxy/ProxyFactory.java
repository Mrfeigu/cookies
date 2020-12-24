package com.delicacy.cookies.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author linzhenghui
 * @date 2020/12/24
 */
public class ProxyFactory implements InvocationHandler {

    private Object object;

    public ProxyFactory(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("sayHi".equals(method.getName())) {
            System.out.println("ProxyFactory#invoke: " + method.getName());
            return method.invoke(object, args);
        }
        return method.invoke(object, args);
    }

    public static<T> T create(T t, Class<?> interfaceClazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyClass = Proxy.getProxyClass(interfaceClazz.getClassLoader(), interfaceClazz);
        return (T)proxyClass.getConstructor(InvocationHandler.class).newInstance(new ProxyFactory(t));
    }


}
