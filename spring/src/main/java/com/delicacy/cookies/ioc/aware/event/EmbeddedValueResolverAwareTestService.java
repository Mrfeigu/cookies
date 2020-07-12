package com.delicacy.cookies.ioc.aware.event;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

/**
 * 获取字符串解析工具
 * @author linzhenghui
 * @date 2020/7/1
 */
@Service
public class EmbeddedValueResolverAwareTestService implements EmbeddedValueResolverAware {

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("执行setEmbeddedValueResolver");
        String s = resolver.resolveStringValue("你好${target.username}, 计算#{3*8}");
        System.out.println(s);
    }

}
