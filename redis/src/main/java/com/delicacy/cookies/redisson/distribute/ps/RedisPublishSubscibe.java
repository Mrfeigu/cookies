package com.delicacy.cookies.redisson.distribute.ps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.codec.SerializationCodec;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author feigu
 * @date 2020/09/28
 */
@Slf4j
@Service
public class RedisPublishSubscibe {

    @Resource
    private RedissonClient redisson;

    public void publish(String topicName, Object msg){
        RTopic topic = redisson.getTopic(topicName, new SerializationCodec());
        topic.publish(msg);
    }

    /**
     * 讲道理, 有点憨 todo 感觉可以在深入一波
     */
    public void subscibe(){

        redisson.getTopic("topic").addListener(Car.class, (charSequence, car) -> {
            System.out.println("onMessage:"+charSequence+"; Thread: "+Thread.currentThread().toString());
            System.out.println(car.getColour()+" price : "+car.getPrice());
        });

    }


}
