package com.delicacy.cookies.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/9/11
 */

@RestController
@RequestMapping("/redisson")
public class RedissonDebugController {



    @GetMapping("/debug")
    private Object debug(){
        // redissonCommonDemo.testSet();
        return "wdnmd";
    }



}
