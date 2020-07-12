package com.delicacy.cookies.aop;

import java.lang.annotation.*;


/**
 * aop目标注解
 * @author linzhenghui
 * @date 2020/7/12
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetAnnotation {

    /**
     * 是否开启记录
     * @return
     */
    boolean value() default true;

}
