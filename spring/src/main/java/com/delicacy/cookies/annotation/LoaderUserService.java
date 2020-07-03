package com.delicacy.cookies.annotation;

import com.delicacy.cookies.service.UserInfoService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 验证通过注解加载Bean
 * @author linzhenghui
 * @date 2020/7/3
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(UserInfoService.class)
public @interface LoaderUserService {

}
