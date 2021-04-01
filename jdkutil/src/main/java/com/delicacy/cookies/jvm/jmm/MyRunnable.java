package com.delicacy.cookies.jvm.jmm;

/**
 * 一个引用的经典例子
 * @author linzhenghui
 * @date 2021/04/01
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        methodOne();
    }

    public void methodOne() {
        int localVariable1 = 45;  // 每个线程都是副本
        MySharedObject localVariable2 = MySharedObject.sharedInstance;  // 公有实例
        methodTwo();
    }

    public void methodTwo() {
        Integer localVariable1 = new Integer(99);  //  私有
        //... do more with local variable.
    }


    public static class MySharedObject {

    //static variable pointing to instance of MySharedObject

    public static final MySharedObject sharedInstance = new MySharedObject();

    //member variables pointing to two objects on the heap

    public Integer object2 = new Integer(22);
    public Integer object4 = new Integer(44);

    public long member1 = 12345;

    }

}