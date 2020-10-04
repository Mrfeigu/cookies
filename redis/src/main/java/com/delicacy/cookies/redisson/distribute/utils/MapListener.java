package com.delicacy.cookies.redisson.distribute.utils;


import org.redisson.api.map.event.EntryCreatedListener;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryRemovedListener;
import org.redisson.api.map.event.EntryUpdatedListener;
import org.springframework.stereotype.Component;

/**
 * https://github.com/redisson/redisson/wiki/7.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%9B%86%E5%90%88
 * redisson的map监听器
 *
 * @author linzhenghui
 * @date 2020/9/29
 */
@Component
public class MapListener implements EntryUpdatedListener, EntryCreatedListener, EntryRemovedListener {


    @Override
    public void onUpdated(EntryEvent event) {
        System.out.println("MapListener监听器开始onUpdated");

        System.out.println(event.getKey());
        System.out.println(event.getValue());

        System.out.println("MapListener监听器结束onUpdated");
    }

    @Override
    public void onCreated(EntryEvent event) {
        System.out.println("MapListener监听器开始onCreated");

        System.out.println(event.getKey());
        System.out.println(event.getValue());

        System.out.println("MapListener监听器结束onCreated");

    }

    @Override
    public void onRemoved(EntryEvent event) {
        System.out.println("MapListener监听器开始onRemoved");

        System.out.println(event.getKey());
        System.out.println(event.getValue());

        System.out.println("MapListener监听器结束onRemoved");
    }


}
