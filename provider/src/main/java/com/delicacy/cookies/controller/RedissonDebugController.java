package com.delicacy.cookies.controller;


import com.delicacy.cookies.entity.UserInfo;
import com.delicacy.cookies.redisson.distribute.utils.*;
import com.delicacy.cookies.redisson.distribute.object.RedisUtils;
import com.delicacy.cookies.redisson.distribute.ps.Car;
import com.delicacy.cookies.redisson.distribute.ps.RedisPublishSubscibe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author linzhenghui
 * @date 2020/9/11
 */
@Slf4j
@RestController
@RequestMapping("/redisson")
public class RedissonDebugController {


    @Resource(name = "redissonRedisUtils")
    private RedisUtils redisCommonUtils;

    @Resource
    private RedisPublishSubscibe redisPublishSubscibe;

    @Resource
    private RedisBlondFilter redisBlondFilter;

    @Resource
    private RedisRateLimiter redisRateLimiter;

    @Resource
    private RedisCollectionMapService redisCollectionMapService;

    @Resource
    private MultiMapDemoService multiMapDemoService;

    @Resource
    private SetDemoService setDemoService;

    @Resource
    private RedisQueueDemoService redisQueueDemoService;

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
    public Object debug7(){
        redisPublishSubscibe.publish("topic", new Car(20000, "blue"));
        return true;
    }

    @GetMapping("/debug8")
    private Object debug8(){
        return redisBlondFilter.filter();
    }

    @GetMapping("/debug9")
    private Object debug9(){
        return redisRateLimiter.limiter();
    }


    @GetMapping("/debug10")
    private Object debug10(){
        String resStr = "真的不是这样";
        try{
            resStr = (String)redisCollectionMapService.lockDemo();
        }catch (Exception ex){
            log.error("redisCollectionMapService#lockDemo", ex);
        }
        return resStr;
    }

    @GetMapping("/debug11")
    private Object debug11(){
        String resStr = "真的不是这样";
        try{
            resStr = (String)redisCollectionMapService.elementEliminate();
        }catch (Exception ex){
            log.error("redisCollectionMapService#lockDemo", ex);
        }
        return resStr;
    }

    @GetMapping("/debug12")
    private Object debug12(){
        return redisCollectionMapService.localCacheMap();
    }

    @GetMapping("/debug13")
    private Object debug13(){
        return redisCollectionMapService.testListener();
    }

    @GetMapping("/debug14")
    private Object debug14(){
        return multiMapDemoService.baseSetMultiMap();
    }

    @GetMapping("/debug15")
    private Object debug15(){
        return multiMapDemoService.baseListMultiMap();
    }

    @GetMapping("/debug16")
    private Object debug16(){
        return multiMapDemoService.evictionMultimap();
    }

    @GetMapping("/debug17")
    private Object debug17(){
        return setDemoService.sortedSetDemo();
    }

    @GetMapping("/debug18")
    private Object debug18(){
        return setDemoService.scoredSortedSetDemo();
    }

    @GetMapping("/debug19")
    private Object debug19(){
        return setDemoService.rLexSortedSetDemo();
    }

    @GetMapping("/debug20")
    private Object debug20(){
        return redisQueueDemoService.dequeDemo();
    }

    @GetMapping("/debug21")
    private Object debug21() throws InterruptedException {
        return redisQueueDemoService.blockingQueueDemo();
    }

    @GetMapping("/debug22")
    private Object debug22() throws InterruptedException {
        return redisQueueDemoService.boundedBlockingQueueDemo();
    }

    @GetMapping("/debug23")
    private Object debug23() throws InterruptedException {
        return redisQueueDemoService.blockingDequeDemo();
    }

    @GetMapping("/debug24")
    private Object debug24() throws InterruptedException {
        return redisQueueDemoService.delayedQueueDemo();
    }

    @GetMapping("/debug25")
    private Object debug25() throws InterruptedException {
        return redisQueueDemoService.priorityQequeDemo();
    }

    @GetMapping("/debug26")
    private Object debug26() throws InterruptedException {
        return redisQueueDemoService.priorityDequeDemo();
    }

    @GetMapping("/debug27")
    private Object debug27() throws InterruptedException {
        return redisQueueDemoService.priorityBlockingQueueDemo();
    }

    @GetMapping("/debug28")
    private Object debug28() throws InterruptedException {
        return redisQueueDemoService.priorityBlockingDequeDemo();
    }


}
