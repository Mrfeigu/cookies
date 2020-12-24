package com.delicacy.cookies.jdkproxy;

/**
 * @author linzhenghui
 * @date 2020/12/23
 */
public class HelloImpl implements Hello {


    @Override
    public String sayHello(String s) {
        System.out.println(s);
        return s;
    }

}
