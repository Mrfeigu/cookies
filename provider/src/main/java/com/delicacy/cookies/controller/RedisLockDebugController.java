package com.delicacy.cookies.controller;

import com.delicacy.cookies.using.MyRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author linzhenghui
 * @date 2020/10/10
 */

@Slf4j
@RestController
@RequestMapping("/redisLock")
public class RedisLockDebugController {

    @Resource
    private MyRedisLock myRedisLock;

    @GetMapping("/lock")
    public Object getLock(){
        return myRedisLock.tryLock("你退后的样子让人感到害怕", "xixixi", 100);
    }

    @GetMapping("/releaseLock")
    public Object releaseLock(){
        return myRedisLock.releaseLock("你退后的样子让人感到害怕", "xixixi");
    }

}
