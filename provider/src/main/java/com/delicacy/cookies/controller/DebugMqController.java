package com.delicacy.cookies.controller;

import com.delicacy.cookies.demo.RabbitMqDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/9/11
 */

@RestController
@RequestMapping("/debug")
public class DebugMqController {

    @Resource
    RabbitMqDemo rabbitMqDemo;

    @GetMapping("debug")
    public Object debug(String msg){
        rabbitMqDemo.mqProduct(msg);
        return "success";
    }


}
