package com.delicacy.cookies.redisson.distribute.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis 分布式限流器，底层算法是令牌桶
 * @author linzhenghui
 * @date 2020/9/29
 */
@Slf4j
@Component
public class RedisRateLimiter implements InitializingBean {

    @Resource
    private RedissonClient redisson;

    /** 限流器*/
    RRateLimiter rateLimiter;

    public Object limiter(){
        // 获取限流令牌
        rateLimiter.acquire(1);
        return "wdf";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rateLimiter = redisson.getRateLimiter("rateLimiter");
        // 最大流速 = 每10秒钟产生1个令牌
        rateLimiter.trySetRate(RateType.PER_CLIENT, 1, 10, RateIntervalUnit.SECONDS);
    }
}
