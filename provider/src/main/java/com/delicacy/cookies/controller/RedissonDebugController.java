package com.delicacy.cookies.controller;


import com.delicacy.cookies.RedisCommonUtils;
import com.delicacy.cookies.entity.UserInfo;
import com.delicacy.cookies.redisson.distribute.filter.RedisBlondFilter;
import com.delicacy.cookies.redisson.distribute.object.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author linzhenghui
 * @date 2020/9/11
 */

@RestController
@RequestMapping("/redisson")
public class RedissonDebugController {


    @Resource(name = "redissonRedisUtils")
    private RedisUtils redisCommonUtils;

    @Resource
    private RedisBlondFilter redisBlondFilter;

    @GetMapping("/debug")
    private Object debug(){
        redisCommonUtils.set("kabagui", "wdnmd", 10000);
        return redisCommonUtils.get("kabagui");
    }

    @GetMapping("/debug2")
    private Object debug2(){
        boolean kabagui = redisCommonUtils.hasKey("kabagui");
        redisCommonUtils.del("kabagui");
        return kabagui;
    }

    @GetMapping("/debug3")
    private Object debug3(){
        Long wdnmd = redisCommonUtils.incr("wdnmd", 1L);
        System.out.println(wdnmd);
        redisCommonUtils.decr("wdnmd", 2L);
        return null;
    }

    @GetMapping("/debug4")
    private Object debug4(){
        Map<String, Object> map = new HashMap<>();
        map.put("zhangsan", new UserInfo(1, "张三", "法外狂徒"));
        map.put("lisi", new UserInfo(2, "李四", "机械先驱"));
        redisCommonUtils.hmset("wdnmd", map);
        return redisCommonUtils.hget("wdnmd", "zhangsan");
    }


    @GetMapping("/debug5")
    private Object debug5(){

        redisCommonUtils.hset("wdnmd", "wangwu", "潮汐之灵");
        redisCommonUtils.hset("zheshiygieceshi", "wangwu", "潮汐之灵");

        redisCommonUtils.hdel("wdnmd", "zhangsan", "lisi");

        boolean b = redisCommonUtils.hHasKey("wdnmd", "wangwu");
        System.out.println(b);

        redisCommonUtils.sSet("wdnmdSet", "yuanhua", "xialuo", "wangduoyu");
        boolean b1 = redisCommonUtils.sHasKey("wdnmdSet", "yuanhua");
        System.out.println(b1);

        long wdnmdSet1 = redisCommonUtils.sGetSetSize("wdnmdSet");
        System.out.println(wdnmdSet1);

        Set<Object> wdnmdSet = redisCommonUtils.sGet("wdnmdSet");
        redisCommonUtils.setRemove("wdnmdSet", "wangduoyu", "xialuo");

        return null;

    }

    @GetMapping("/debug6")
    private Object debug6(){

        // set
        redisCommonUtils.lSet("wdnmdList", "zhangsan");
        List<Object> list = new ArrayList<>();
        list.add("wo");
        list.add("zhale");
        list.add("zhende");
        redisCommonUtils.lSet("wdnmdList", list);

        // get
        long wdnmdList = redisCommonUtils.lGetListSize("wdnmdList");
        redisCommonUtils.lGet("wdnmdList", 0, wdnmdList);

        Object wdnmdList1 = redisCommonUtils.lGetIndex("wdnmdList", 0);

        redisCommonUtils.lUpdateIndex("wdnmdList", 0, "qiezi");
        redisCommonUtils.lRemove("wdnmdList", 1, "qiezi");

        return null;
    }

    @GetMapping("/debug7")
    private Object debug7(){
        return redisBlondFilter.filter();
    }



}
