package com.delicacy.cookies;

import com.delicacy.cookies.annotation.LoaderUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@LoaderUserService
@SpringBootApplication
public class CookiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookiesApplication.class, args);
	}

}
