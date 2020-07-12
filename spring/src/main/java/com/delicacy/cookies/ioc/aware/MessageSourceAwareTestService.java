package com.delicacy.cookies.ioc.aware;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Service;

/**
 * 没发现有啥作用
 *
 * @author linzhenghui
 * @date 2020/7/1
 */

@Service
public class MessageSourceAwareTestService implements MessageSourceAware {

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("执行setMessageSource");
    }

}
