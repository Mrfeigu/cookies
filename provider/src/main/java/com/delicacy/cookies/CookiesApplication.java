package com.delicacy.cookies;

import com.delicacy.cookies.ioc.annotation.LoaderUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author linzhenghui
 */
@EnableRetry
@LoaderUserService
@SpringBootApplication
public class CookiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookiesApplication.class, args);
	}

}
