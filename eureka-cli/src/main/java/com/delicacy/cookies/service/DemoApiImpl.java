package com.delicacy.cookies.service;

import com.delicacy.feignclient.demo1.DemoApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linzhenghui
 * @date 2021/3/2
 */
@RestController
@RequestMapping("/demoApi")
public class DemoApiImpl implements DemoApi {

    @Override
    public String print(String str) {
        return "DemoApiImpl" + str;
    }

}
