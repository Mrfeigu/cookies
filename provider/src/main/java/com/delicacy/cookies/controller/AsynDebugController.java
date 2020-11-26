package com.delicacy.cookies.controller;


import com.delicacy.cookies.asyn.AsynDebugService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author feigu
 * @date 2020/11/26
 */

@RestController
@RequestMapping("/asyn")
public class AsynDebugController {

    @Resource
    private AsynDebugService asynDebugService;

    @GetMapping("/debug")
    public Object debug() {
        return asynDebugService.test();
    }


}
