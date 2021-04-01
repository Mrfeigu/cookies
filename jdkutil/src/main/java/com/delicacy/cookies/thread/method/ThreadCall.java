package com.delicacy.cookies.thread.method;

import com.delicacy.cookies.thread.pool.ThreadPoolService;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Callable的应用
 * @author linzhenghui
 * @date 2021/4/1
 */
public class ThreadCall {

    private static final ThreadPoolService THREAD_POOL = new ThreadPoolService();


    /**
     * 调用信息
     */
    public static class Call implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getId());
            return 19978;
        }

    }

    public static void main(String[] args) throws Exception {

        Future<Integer> submit = THREAD_POOL.submit(new Call());
        Future<Integer> submit1 = THREAD_POOL.submit(new Call());
        Future<Integer> submit2 = THREAD_POOL.submit(new Call());
        Future<Integer> submit3 = THREAD_POOL.submit(new Call());

        int result = submit.get() + submit1.get() + submit2.get() + submit3.get();

        System.out.println(result);

    }

}
