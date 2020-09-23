package com.delicacy.cookies.testdemo;

import com.delicacy.cookies.thread.NameThreadFactory;
import com.delicacy.cookies.util.BatchUtils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试BatchUtils是否可以并发，可
 * @author linzhenghui
 * @date 2020/9/23
 */
public class CurrentTestBatchUtilsDemo {

    /**
     * 随缘线程池
     */
    public static ExecutorService executorService = new ThreadPoolExecutor(4,
            4,
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000),
            new NameThreadFactory("cookies-test"),
            new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws InterruptedException {

        List<Integer> sum = new LinkedList<>();

        for (int i = 0; i < 100; i++) {

            executorService.submit(() -> {
                sum.addAll(testDemo());
            });

        }

        Thread.sleep(10000L);

        sum.sort(Comparator.comparing(Integer::intValue));
        System.out.println("ending...");

    }


    public static List<Integer> testDemo(){

        List<Integer> sumList = new LinkedList<>();

        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 50; i++) {
            list.add(i);
        }

        BatchUtils.exe(list, 10, temp->{
            sumList.addAll(temp);
            System.out.println(Thread.currentThread().getId());
        });

        return sumList;
    }




}
