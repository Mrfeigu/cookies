package com.delicacy.cookies.retry;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author linzhenghui
 * @date 2020/11/24
 */

@Component("retryListener")
public class RetryListener extends RetryListenerSupport {

    /***
     * 事件每次发生重试都会被调用
     * @param context
     * @param callback
     * @param throwable
     * @param <T>
     * @param <E>
     */
    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        System.out.println("监听重试错误");
    }


}
