package com.delicacy.cookies.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/11/13
 */
@Slf4j
@RestController
@RequestMapping("/zookeeper")
public class ZookeeperDebugController {

    @GetMapping("/debug")
    public Object test(String test){
        return null;
    }

}
