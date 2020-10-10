package com.delicacy.cookies.thread.task;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author linzhenghui
 * @date 2020/10/9
 */
public class DelayTask implements Runnable, Delayed {

    private long expireTime;

    private BaseTask task;

    DelayTask(long expireTime, BaseTask task){
        this.expireTime = expireTime;
        this.task = task;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expireTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long td = this.getDelay(TimeUnit.MILLISECONDS);
        long od = o.getDelay(TimeUnit.MILLISECONDS);
        return Long.compare(td, od);
    }

    @Override
    public void run() {
        task.run();
    }

}
