package com.delicacy.cookies.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/10/15
 */

@Slf4j
@RestController
@RequestMapping("/http")
public class HttpTemplateDebugController {


    @Resource(name = "oKHttp3RestTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/testLogin")
    public Object test(){
        Object forObject = restTemplate.getForObject("http://app.61draw.com/v1/student/loginV2?account=13097324203&password=670b14728ad9902aecba32e22fa4f6bd&deviceId=F2B6AF25-AF8A-4197-8A4D-A11D29FC5363", Object.class);
        return forObject;
    }




}
