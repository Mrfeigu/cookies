package com.delicacy.cookies.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {


    private static int count = 500;

    public static MyLock lock = new MyLock();

    public static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                // 加锁

                if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {

                    // 处理
                    if (count > 0) {
                        System.out.println("当前count:" + count);
                        count--;
                    } else {
                        System.out.println("count已经小于0");
                    }

                    // 释放锁
                    lock.unLock();
                }else{
                    count--;
                    System.out.println("当前线程超时没有拿到锁：" + count);
                }


            }).start();
        }
    }


}
