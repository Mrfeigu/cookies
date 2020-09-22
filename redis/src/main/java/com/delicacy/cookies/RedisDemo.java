package com.delicacy.cookies;

import com.delicacy.cookies.utils.RedisUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private RedisUtils redisUtils;

    public Object test(){
        TestRedisDemoEntity testRedisDemoEntity = new TestRedisDemoEntity();
        testRedisDemoEntity.setUserId(1);
        testRedisDemoEntity.setAge(1);
        testRedisDemoEntity.setUserName("feigu");
        redisUtils.set("ahhh", testRedisDemoEntity);
        return redisUtils.get("ahhh");
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
