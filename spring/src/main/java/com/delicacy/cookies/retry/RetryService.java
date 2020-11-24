package com.delicacy.cookies.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/11/24
 */
@Slf4j
@Service
public class RetryService {

    /**
     * maxAttempts 所有尝试次数（包括第一次）
     * include 包括重试的异常
     * exclude 不包括重试的异常
     * 重试补偿机制 backoff = @Backoff(delay = 1000L, multiplier = 1.5), 延迟1秒，下次延迟时间为2秒，下一次为3.5秒
     *
     * @return
     */
    @Retryable(maxAttempts = 5, include = {Exception.class},
            backoff = @Backoff(delay = 1000L, multiplier = 1.5), listeners = {"retryListener"})
    public Object testRetry() {
        // 实际上发生了异常，重试阶段会吞掉异常，最后失败还是会抛出异常
        System.out.println(System.currentTimeMillis());
        System.out.println("发生重试");
        throw new RuntimeException("异常异常");
    }



}
