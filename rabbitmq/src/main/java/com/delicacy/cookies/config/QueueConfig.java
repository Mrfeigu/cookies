package com.delicacy.cookies.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author linzhenghui
 * @date 2020/9/11
 */
@Slf4j
// @Component
public class QueueConfig {

    @Bean(name = "monitorReportReduceQueue")
    Queue queue() {
        return new Queue("monitor_report_reduce_queue", false);
    }


}
