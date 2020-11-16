package com.delicacy.cookies.testdemo;


import java.text.MessageFormat;

/**
 * @author linzhenghui
 * @date 2020/7/3
 */

public class Test {

    public static void main(String[] args) {
       String template = "{0}商品的兑换码为{1}，有效期{2}，请及时兑换，点击这里查看兑换流程";
       Object[] params = {"京东购物卡100元", "京东卡号:JDV50240006333000091；密码： BDEB-F82A-FF3A-99F9", "无限"};
        String format = MessageFormat.format(template, params);
        System.out.println(format);
    }

}
