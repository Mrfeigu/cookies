package com.delicacy.cookies.event;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/10/21
 */
@Slf4j
@Service
public class ListenerService {

    @Subscribe
    public void onDemoEvent(DemoEvent demoEvent){
        log.info("ListenerService#onDemoEvent demoEvent:{}", demoEvent.toString());
    }

}
