package com.delicacy.cookies.asyn;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 注解@Asyn实现
 *
 * @author feigu
 * @date 2020/11/26
 */
@Slf4j
@Service
public class AsynDebugService {

    @Resource
    private AsynTestService asynTestService;

    public Object test() {
        System.out.println("AsynDebugService#test 这是同步的");
        asynTestService.test();
        return "AsynDebugService#test";
    }


}
