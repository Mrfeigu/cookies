package com.delicacy.cookies.controller;

import com.delicacy.cookies.demo1.Demo1Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2021/3/2
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Resource
    private Demo1Service demo1Service;

    @GetMapping("/test")
    public Object test() {
        return demo1Service.test();
    }

}
