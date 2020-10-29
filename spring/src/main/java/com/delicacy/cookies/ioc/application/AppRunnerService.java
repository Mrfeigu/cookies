package com.delicacy.cookies.ioc.application;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;


/**
 * Spring启动后，能获取jvm启动参数，也能执行一些事情
 *
 * @author linzhenghui
 * @date 2020/10/29
 */
@Service
public class AppRunnerService implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("runner");
    }

}
