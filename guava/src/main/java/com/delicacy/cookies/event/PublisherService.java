package com.delicacy.cookies.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/10/21
 */
@Slf4j
@Service
public class PublisherService {

    @Resource
    private EventService eventService;

    public Object post(String s){
        eventService.postAsyncEvent(DemoEvent.builder().eventName(s + ": AsyncEvent").build());
        eventService.postEvent(DemoEvent.builder().eventName(s + ": postEvent").build());
        return s;
    }


}
