package com.delicacy.cookies.controller;

import com.delicacy.cookies.vo.DebugVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author linzhenghui
 * @date 2020/11/25
 */
@RestController
@RequestMapping("/valid")
public class ValidDebugController {

    @PostMapping("/debug")
    public Object debug(@RequestBody @Valid DebugVo debugVo) {
        return "OK";
    }


}
