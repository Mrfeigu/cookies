package com.delicacy.cookies.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author linzhenghui
 * @date 2020/10/21
 */
@Slf4j
@Service
public class EventService implements DisposableBean {

    /** gson*/
    private Gson gson;
    /** 线程池*/
    private ExecutorService executor;
    /** 同步bus*/
    private EventBus eventBus;
    /** 异步bus*/
    private AsyncEventBus asyncEventBus;

    public EventService() {
        gson = new Gson();
        executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
        eventBus = new EventBus();
        asyncEventBus = new AsyncEventBus(executor);
        log.info("EventService is init success.");
    }

    /**
     * 发送同步事件
     * @param event
     */
    public void postEvent(Object event) {
        log.info("eventService#postEvent event=" + gson.toJson(event));
        eventBus.post(event);
    }

    /**
     * 发布异步事件
     * @param event
     */
    public void postAsyncEvent(Object event) {
        log.info("eventService#postAsyncEvent event=" + gson.toJson(event));
        asyncEventBus.post(event);
    }

    /**
     * 注册监听器
     * @param listener
     */
    public void registerLister(Object listener) {
        eventBus.register(listener);
        asyncEventBus.register(listener);
    }


    @Override
    public void destroy() throws Exception {
        executor.shutdown();
    }

}
