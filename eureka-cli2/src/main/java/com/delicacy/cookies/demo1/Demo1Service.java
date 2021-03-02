package com.delicacy.cookies.demo1;

import com.delicacy.feignclient.demo1.DemoApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2021/3/2
 */
@Service
public class Demo1Service {

    @Resource
    private DemoApi demoApi;

    public String test(){
        String dess = demoApi.print("dess");
        System.out.println(dess);
        return dess;
    }

}
