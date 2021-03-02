package com.delicacy.feignclient.demo1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author linzhenghui
 * @date 2021/3/2
 */
@FeignClient(name = "EUREKA-CLI", path = "/demoApi")
public interface DemoApi {

    /**
     * demo接口
     * @param str
     * @return
     */
    @GetMapping(value = "/print")
    String print(@RequestParam(value = "str", required = false) String str);

}
