package com.delicacy.cookies.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 加载环境
 * @author linzhenghui
 * @date 2020/6/28
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.delicacy.cookies.aop")
public class SpringConfig {

}
