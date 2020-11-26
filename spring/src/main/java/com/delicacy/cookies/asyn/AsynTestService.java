package com.delicacy.cookies.asyn;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 注解@Asyn实现
 *
 * @author feigu
 * @date 2020/11/26
 */
@Slf4j
@Service
public class AsynTestService {


    @Async
    public void test() {

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("AsynTestService#test 异步了吗");
        System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
    }


}
