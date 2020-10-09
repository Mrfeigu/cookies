package com.delicacy.cookies.controller;

import com.delicacy.cookies.thread.task.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private Schedule schedule;

    @GetMapping("/debugTask")
    public Object debugTask(){
        schedule.addDemoTask();
        return "ahhh";
    }

    @GetMapping("/debugDelayedTask")
    public Object debugDelayedTask(){
        schedule.addDelayedTask();
        return "ahhhh";
    }




}
