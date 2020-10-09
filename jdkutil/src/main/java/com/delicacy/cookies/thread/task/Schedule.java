package com.delicacy.cookies.thread.task;

import com.delicacy.cookies.thread.pool.ThreadPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
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

    private BlockingQueue<BaseTask> blockingQueue = new LinkedBlockingQueue<>();

    @Override
    public void afterPropertiesSet() {
        Thread thread = new Thread(() -> {
            running();
        });
        thread.start();
        log.info("Schedule#afterPropertiesSet start running:{}", thread.getName());
    }

    public void running(){
        while(true){
            // 初始化一直拿任务
            try{
                BaseTask task = blockingQueue.take();
                threadPoolService.execute(task);
            }catch (Exception ex){
                log.error("Schedule#running running.", ex);
            }
        }
    }


}
