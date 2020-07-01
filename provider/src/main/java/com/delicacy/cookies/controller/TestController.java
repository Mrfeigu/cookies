package com.delicacy.cookies.controller;

import com.delicacy.cookies.postorocessor.BeanDefinitionRegistryPostProcessorTestService;
import com.delicacy.cookies.service.TargetService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@DependsOn("targetService2")
@RestController
@RequestMapping("/ahh")
public class TestController {


    @Resource(name = "targetService2")
    private TargetService targetService;

    @GetMapping("/test")
    public Object test(String var){
        return "";
    }

    @GetMapping("/testTarget")
    public Object testTarget(){
        return targetService.printlnMsg();
    }

}


