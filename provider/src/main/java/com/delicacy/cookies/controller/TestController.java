package com.delicacy.cookies.controller;

import com.delicacy.cookies.RedisDemo;
import com.delicacy.cookies.ioc.service.TargetService;
import com.delicacy.cookies.ioc.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@DependsOn("targetService2")
@RestController
@RequestMapping("/ahh")
public class TestController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RedisDemo redisDemo;

    @GetMapping("/test")
    public Object test(String var){
        return "";
    }

    @GetMapping("/testTarget")
    public Object testTarget(){
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        log.info("TestController#  list:{}", list);
        return null;
    }

    @GetMapping("/testRedis")
    public Object testRedis(){
        return redisDemo.test();
    }

}


