package com.delicacy.cookies.controller;

import com.delicacy.cookies.NettyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/11/17
 */

@RestController
@RequestMapping("/netty")
public class NettyDebugController {

    @Resource
    private NettyTestService nettyTestService;

    @GetMapping("/debug")
    public Object debug(String str) {
        return nettyTestService.test(str);
    }


}
