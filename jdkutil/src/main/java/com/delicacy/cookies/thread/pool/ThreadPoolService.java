package com.delicacy.cookies.thread.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author linzhenghui
 * @date 2020/10/9
 */
@Slf4j
@Service
public class ThreadPoolService {

    public ExecutorService threadPoolExecutor;

    {

        /**
         * keepAliveTime：线程空闲的销毁时间
         *
         *
         * 拒绝策略：
         * DiscardPolicy 直接丢弃
         * DiscardOldestPolicy 丢弃队列中最老的任务
         * AbortPolicy 抛异常
         * CallerRunsPolicy 将任务分给调用线程来执行
         */
        threadPoolExecutor = new ThreadPoolExecutor(4,
                4,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1000),
                new NameThreadFactory("cookies"),
                new ThreadPoolExecutor.DiscardPolicy());

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

    }

    private void shutdown() {
        threadPoolExecutor.shutdown();
    }

    public void execute(Runnable task) {
        threadPoolExecutor.execute(task);
    }

    public <T> Future<T> submit(Callable<T> task) {
        return threadPoolExecutor.submit(task);
    }

}
