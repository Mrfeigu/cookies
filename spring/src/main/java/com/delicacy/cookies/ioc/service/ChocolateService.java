package com.delicacy.cookies.ioc.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2021/1/16
 */

@Service
public class ChocolateService {

    @Resource
    private TargetService targetService;

    public Object msg() {
        String s = targetService.printlnMsg();
        System.out.println(s);
        return s;
    }


}
