package com.delicacy.cookies.controller;

import com.delicacy.cookies.BeanDefinitionRegistryPostProcessor.TestService;
import com.delicacy.cookies.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ahh")
public class TestController {

    @Resource(name = "testService")
    private TestService testService;

    @Resource
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


