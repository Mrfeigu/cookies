package com.delicacy.cookies;

import com.delicacy.cookies.spi.Robot;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ServiceLoader;

@SpringBootTest
class CookiesApplicationTests {

	@Test
	void contextLoads() {
	}


	/**
	 * spi测试
	 */
	@Test
	void test1(){
		ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
		System.out.println("Java SPI");
		serviceLoader.reload();
		serviceLoader.forEach(Robot::sayHello);

	}

}
