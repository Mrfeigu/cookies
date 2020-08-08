package com.delicacy.cookies.testdemo;

/**
 * @author linzhenghui
 * @date 2020/7/28
 */
public class TestString {

    public static void main(String[] args) {
        // 都进了常量池
        String s = "123";
        String s1 = "123";
        System.out.println(s == s1);
        // 都是new 出来就不进常量池了
        String s2 = new String("123");
        String s3 = new String("123");
        System.out.println(s2 == s3);
        // 进行有效判断时，如果常量池有，intern是不会重复的，换句话说就是常量池是不会重复的
        String intern = s2.intern();
        System.out.println(intern == s);
        //
        System.out.println(s2 == s);
        //
        String s4 = new String(s);
        System.out.println(s4 == s);
    }

}
