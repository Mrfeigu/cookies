package com.delicacy.cookies.controller;

import com.delicacy.cookies.retry.RetryService;
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
@RequestMapping("/spring/retry")
public class SpringRetryDebugController {

    @Resource
    private RetryService retryService;


    @GetMapping("/debug")
    public Object test(String test){
        return retryService.testRetry();
    }

}
