package com.delicacy.cookies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.delicacy.cookies", "com.delicacy.feignclient"})
@EnableFeignClients(basePackages = "com.delicacy.feignclient")
public class EurekaCli2Application  {

    public static void main(String[] args) {
        SpringApplication.run(EurekaCli2Application.class, args);
    }

}
