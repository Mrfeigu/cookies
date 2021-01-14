package com.delicacy.cookies.chain.annotation;


import java.lang.annotation.*;

/**
 * @author linzhenghui
 * @date 2021/1/14
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DessertTaskType {

    /**
     * 任务类型
     */
    String type();

    /**
     * 顺序
     */
    int order() default 0;

}
