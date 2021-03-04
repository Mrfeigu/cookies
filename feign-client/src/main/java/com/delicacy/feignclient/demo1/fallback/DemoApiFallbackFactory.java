package com.delicacy.feignclient.demo1.fallback;

import com.delicacy.feignclient.demo1.DemoApi;
import org.springframework.cloud.openfeign.FallbackFactory;
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
                return "大哥大嫂过年好";
            }

        };

    }
}
