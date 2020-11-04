package com.delicacy.cookies.controller;

import com.delicacy.cookies.spi.Robot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ServiceLoader;

/**
 * @author linzhenghui
 * @date 2020/10/22
 */


@RestController
@RequestMapping("/SpiDebug")
public class SpiDebugController {

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    @GetMapping("/test")
    public Object test(){
        // 所以我还没搞懂spi有啥用，还是一样要重新编译，并不能运行时加载
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        serviceLoader.reload();
        serviceLoader.forEach(Robot::sayHello);
        return "success";
    }



}
