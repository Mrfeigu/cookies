package com.delicacy.cookies.jvm.collector;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * 查看当前虚拟机的垃圾回收算法
 *
 * @author linzhenghui
 * @date 2021/2/20
 */
public class GcCollector {


    public static void main(String[] args) {

        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println(gcBean.getName());
        }

    }


}
