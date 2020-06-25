package com.delicacy.cookies.controller;

import com.delicacy.cookies.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ahh")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public Object test(String var){
        return testService.testStr(var);
    }


}


