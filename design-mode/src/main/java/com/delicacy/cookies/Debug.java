package com.delicacy.cookies;

import com.delicacy.cookies.chain.TaskFilterPipeline;
import com.delicacy.cookies.chain.task.BaseTaskWrapper;
import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author linzhenghui
 * @date 2021/1/14
 */
public class Debug {

    public static void main(String[] args) {

        ApplicationContext ac = new AnnotationConfigApplicationContext("com.delicacy.cookies.chain");
        TaskFilterPipeline tfp = ac.getBean(TaskFilterPipeline.class);
        BaseTaskWrapper<String> baseTaskWrapper = new BaseTaskWrapper<String>("槐树", "篮网总冠军");
        List<Object> resList = Lists.newArrayList();
        List<Object> filter = tfp.filter(baseTaskWrapper, resList);
        System.out.println("ending...");

    }



}
