package com.delicacy.cookies.lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {


    private static int count = 30;

    public static MyLock lock = new MyLock();

    public static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 加锁
                try {
                    lock.lock();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

                // 处理
                if (count > 0) {
                    System.out.println("当前count:" + count);
                    count--;
                } else {
                    System.out.println("count已经小于0");
                }

                // 释放锁
                try {
                    lock.unLock();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }


}
