package com.delicacy.cookies.testdemo;

import com.delicacy.cookies.thread.pool.ThreadPoolService;
import com.delicacy.cookies.util.BatchUtils;

import javax.annotation.Resource;
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


    static ThreadPoolService threadPoolService = new ThreadPoolService();

    public static void main(String[] args) throws InterruptedException {

        List<Integer> sum = new LinkedList<>();

        for (int i = 0; i < 100; i++) {

            threadPoolService.execute(() -> {
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
