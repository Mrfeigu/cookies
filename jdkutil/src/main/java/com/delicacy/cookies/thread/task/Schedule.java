package com.delicacy.cookies.thread.task;

import com.delicacy.cookies.thread.pool.ThreadPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author linzhenghui
 * @date 2020/10/9
 */
@Slf4j
@Service
public class Schedule implements InitializingBean {

    @Resource
    private ThreadPoolService threadPoolService;
    /** 普通任务队列*/
    private BlockingQueue<BaseTask> blockingQueue = new LinkedBlockingQueue<>();
    /** 延迟队列*/
    private BlockingQueue<DelayTask> delayQueue  = new DelayQueue<>();

    public void addDemoTask(){
        DemoTask task = new DemoTask();
        blockingQueue.add(task);
    }

    public void addDelayedTask(){
        DemoTask task = new DemoTask();
        DelayTask delayTask = new DelayTask(System.currentTimeMillis() + 2000, task);
        delayQueue.add(delayTask);
    }

    @Override
    public void afterPropertiesSet() {

        Thread thread1 = new Thread(() -> {
            normalRunning();
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            delayedRunning();
        });
        thread2.start();

        log.info("Schedule#afterPropertiesSet start running:{}", thread2.getName());
        log.info("Schedule#afterPropertiesSet start running:{}", thread1.getName());
    }

    private void delayedRunning(){
        while(true){
            // 初始化一直拿任务
            try{
                DelayTask task = delayQueue.take();
                threadPoolService.execute(task);
            }catch (Exception ex){
                log.error("Schedule#delayedRunning running.", ex);
            }
        }
    }

    private void normalRunning(){
        while(true){
            // 初始化一直拿任务
            try{
                BaseTask task = blockingQueue.take();
                threadPoolService.execute(task);
            }catch (Exception ex){
                log.error("Schedule#normalRunning running.", ex);
            }
        }
    }


}
