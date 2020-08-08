package com.delicacy.cookies.ioc.aware;

import com.delicacy.cookies.ioc.aware.dto.UserDto;
import com.delicacy.cookies.ioc.aware.event.UserApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 应用时间发布器,用于发布事件
 * @author linzhenghui
 * @date 2020/7/1
 */
@Service
public class ApplicationEventPublisherAwareTestService implements ApplicationEventPublisherAware {

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("执行setApplicationEventPublisher");
        UserApplicationEvent userApplicationEvent = new UserApplicationEvent(this, new UserDto("潮汐之灵张三", 13));
        applicationEventPublisher.publishEvent(userApplicationEvent);
    }
}
