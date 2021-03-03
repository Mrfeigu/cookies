package com.delicacy.feignclient.demo1.fallback;

import com.delicacy.feignclient.demo1.DemoApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 熔断类
 * @author linzhenghui
 * @date 2021/3/3
 */
@Component
public class DemoApiFallbackFactory implements FallbackFactory<DemoApi> {

    @Override
    public DemoApi create(Throwable cause) {

        return new DemoApi() {
            @Override
            public String print(String str) {
                return cause.getMessage();
            }

        };

    }
}
