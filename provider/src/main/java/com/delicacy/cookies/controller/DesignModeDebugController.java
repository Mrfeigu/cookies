package com.delicacy.cookies.controller;

import com.delicacy.cookies.strategy.test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/11/5
 */
@RestController
@RequestMapping("/DesignMode")
public class DesignModeDebugController {

    @Resource
    private test test;

    @GetMapping("/debug")
    public Object debug(int i){
        test.test(i);
        return i;
    }


}
