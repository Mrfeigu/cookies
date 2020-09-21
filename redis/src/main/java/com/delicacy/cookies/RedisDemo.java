package com.delicacy.cookies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author linzhenghui
 * @date 2020/9/15
 */
@Service
public class RedisDemo {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Object test(){
        TestRedisDemoEntity testRedisDemoEntity = new TestRedisDemoEntity();
        testRedisDemoEntity.setUserId(1);
        testRedisDemoEntity.setAge(1);
        testRedisDemoEntity.setUserName("feigu");
        redisTemplate.opsForValue().set("ahhh", testRedisDemoEntity);
        return redisTemplate.opsForValue().get("ahhh");
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(staticName = "of")
    private static class TestRedisDemoEntity implements Serializable {
        private Integer userId;
        private Integer age;
        private String userName;
    }

}
