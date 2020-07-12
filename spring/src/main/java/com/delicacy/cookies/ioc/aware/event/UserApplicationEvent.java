package com.delicacy.cookies.ioc.aware.event;

import com.delicacy.cookies.ioc.aware.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 事件体
 * @author linzhenghui
 * @date 2020/7/1
 */
@Getter
@Setter
public class UserApplicationEvent extends ApplicationEvent {

    private UserDto userDto;


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UserApplicationEvent(Object source, UserDto userDto) {
        super(source);
        this.userDto = userDto;
    }
}
