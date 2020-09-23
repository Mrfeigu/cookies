package com.delicacy.cookies.thread;

import com.sun.istack.internal.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linzhenghui
 * @date 2020/9/23
 */
public class NameThreadFactory implements ThreadFactory {

    private AtomicInteger threadNumber = new AtomicInteger();

    private String threadName;

    public NameThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, threadName + "-" + threadNumber.incrementAndGet());
    }

}
