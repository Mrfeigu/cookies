package com.delicacy.cookies.aware.event;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * 资源获取，能够获取外部文件
 * 都是refresh的finishBeanFactoryInitialization(beanFactory);重新装载bean的时候发现
 * @author linzhenghui
 * @date 2020/7/1
 */
@Service
public class ResourceLoaderAwareTestService implements ResourceLoaderAware {

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("执行setResourceLoader");
        Resource resource = resourceLoader.getResource("application.properties");
        String filename = resource.getFilename();
    }



}
