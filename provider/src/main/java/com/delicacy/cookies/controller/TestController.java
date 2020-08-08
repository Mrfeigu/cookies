package com.delicacy.cookies.controller;

import com.delicacy.cookies.ioc.service.TargetService;
import com.delicacy.cookies.ioc.service.UserInfoService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@DependsOn("targetService2")
@RestController
@RequestMapping("/ahh")
public class TestController {


    @Resource(name = "targetService3")
    private TargetService targetService;

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/test")
    public Object test(String var){
        return "";
    }

    @GetMapping("/testTarget")
    public Object testTarget(){
        Object o = userInfoService.sayHello();
        return targetService.printlnMsg();
    }


}


