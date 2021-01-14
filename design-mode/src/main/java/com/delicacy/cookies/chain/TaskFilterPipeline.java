package com.delicacy.cookies.chain;

import com.delicacy.cookies.chain.annotation.DessertTaskType;
import com.delicacy.cookies.chain.filter.AbstractTaskFilter;
import com.delicacy.cookies.chain.task.BaseTaskWrapper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author linzhenghui
 * @date 2021/1/14
 */
@Component
public class TaskFilterPipeline implements ApplicationContextAware {

    private List<AbstractTaskFilter> filters = Lists.newArrayList();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        // 初始化与排序
        Multimap<Integer, AbstractTaskFilter> map = HashMultimap.create();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(DessertTaskType.class);
        beansWithAnnotation.forEach((k, v) -> {
            int key = v.getClass().getAnnotation(DessertTaskType.class).order();
            if(v instanceof AbstractTaskFilter){
                map.put(key, (AbstractTaskFilter)v);
            }
        });

        map.keySet()
                .stream()
                .sorted(Comparator.comparing(Integer::intValue))
                .forEach( key -> {
                    filters.addAll(map.get(key));
                });

    }

    public List<Object> filter(BaseTaskWrapper<?> baseTaskWrapper, List<Object> resList) {
        filters.forEach((filter) -> {
            try{
                filter.doFilter(baseTaskWrapper, resList);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return resList;
    }

}
