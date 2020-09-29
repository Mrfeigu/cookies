package com.delicacy.cookies.redisson.distribute.utils;

import com.delicacy.cookies.redisson.entity.Hero;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RFuture;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * https://github.com/redisson/redisson/wiki/7.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%9B%86%E5%90%88
 * redisson映射集合Map
 * @author linzhenghui
 * @date 2020/9/29
 */

@Slf4j
@Service
public class RedisCollectionMap {

    @Resource
    private RedissonClient redisson;

    public Object map(){
        Map<String, Object> map = Maps.newHashMap();
        map.put("崔斯特", new Hero(1, "崔斯特", "卡牌大师"));
        redisson.getMap("RedisCollectionMap").putAll(map);
        RMap<String, Object> redissonMap = redisson.getMap("RedisCollectionMap");

        redissonMap.fastPut("张学友", new Hero(3, "张学友", "乌蝇哥"));

        // 异步写
        RFuture<Object> objectRFuture = redissonMap.putAsync("劫", new Hero(2, "劫", "影流之主"));

        return null;
    }


}
