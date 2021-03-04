package com.delicacy.cookies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author linzhenghui
 * @date 2021/01/19
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaCliApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaCliApplication.class, args);
    }

}
