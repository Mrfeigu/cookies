package com.delicacy.cookies.thread.localvariate;

import java.text.SimpleDateFormat;

/**
 * @author linzhenghui
 * @date 2021/4/6
 */
public class ThreadLocalDemo {

    /**
     * 时间格式方式，带初始化
     * 每个线程拿到的都是一个新的SimpleDateFormat对象
     *
     */
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = ThreadLocal.withInitial(
            ()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    );

    /**
     * 普通变量，不带初始化
     * 每个线程拿到的都是一个新的SimpleDateFormat对象
     */
    public static final ThreadLocal<Integer> SIGN_VARIATE = new ThreadLocal<>();


    public static void main(String[] args) {

        new Thread(()->{

            String threadIdentifying = Thread.currentThread().getId() + "-" + Thread.currentThread().getName();
            SIGN_VARIATE.set(1);
            System.out.println(threadIdentifying + " date:" + System.identityHashCode(DATE_FORMAT.get()) + " variate:" + SIGN_VARIATE.get().intValue());
            DATE_FORMAT.remove();
        }).start();


        new Thread(()->{

            String threadIdentifying = Thread.currentThread().getId() + "-" + Thread.currentThread().getName();
            SIGN_VARIATE.set(2);
            System.out.println(threadIdentifying + " date:" + System.identityHashCode(DATE_FORMAT.get()) + " variate:" + SIGN_VARIATE.get().intValue());
            DATE_FORMAT.remove();
        }).start();


    }




}
