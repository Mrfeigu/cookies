package com.delicacy.cookies.filelistener;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 文件监听，需要common-io包
 *
 * @author linzhenghui
 * @date 2020/10/29
 */
@Component
public class FileListener extends FileAlterationListenerAdaptor implements ApplicationRunner {

    @Override
    public void onFileChange(final File file) {
        // 这里可以做出文件改变的修改
        System.out.println("flie change fileName : " + file.getName());
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 选择监听路径
        FileAlterationObserver observer = new FileAlterationObserver(new File("/root/my.txt"));
        // 向监听者添加监听器，并注入业务服务
        observer.addListener(this);
        // 开启监听
        new FileAlterationMonitor(1000L, observer).start();
    }
}
