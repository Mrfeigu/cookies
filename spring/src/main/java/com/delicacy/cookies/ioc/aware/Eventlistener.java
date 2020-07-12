package com.delicacy.cookies.ioc.aware;

import com.delicacy.cookies.ioc.aware.dto.UserDto;
import com.delicacy.cookies.ioc.aware.event.UserApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * 事件监听者
 * @author linzhenghui
 * @date 2020/7/1
 */
@Service
public class Eventlistener implements ApplicationListener<UserApplicationEvent> {

    @Override
    public void onApplicationEvent(UserApplicationEvent event) {
        System.out.println("执行onApplicationEvent");
        UserDto userDto = event.getUserDto();
        System.out.println(userDto.toString());
        Object source = event.getSource();
    }
}
