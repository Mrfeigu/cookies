package com.delicacy.cookies.controller;

import com.delicacy.cookies.event.PublisherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/10/21
 */

@RestController
@RequestMapping("/GuavaDebug")
public class GuavaDebugController {

    @Resource
    private PublisherService publisherService;

    @GetMapping("debug1")
    public Object debug(String s){
        return publisherService.post(s);
    }



}
