package com.delicacy.cookies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author linzhenghui
 * @date 2021/01/19
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaCliApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaCliApplication.class, args);
    }

}
