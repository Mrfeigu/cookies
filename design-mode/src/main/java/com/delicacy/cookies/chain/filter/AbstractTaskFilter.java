package com.delicacy.cookies.chain.filter;

import com.delicacy.cookies.chain.task.BaseTaskWrapper;

import java.util.List;

/**
 * 基础责任
 * @author linzhenghui
 * @date 2021/1/14
 */
public abstract class AbstractTaskFilter {

    /**
     * 过滤方法
     * @param baseTaskWrapper 任务类型
     * @param resList 返回结果链
     * @return
     */
        public abstract void doFilter(BaseTaskWrapper<?> baseTaskWrapper, List<Object> resList);


}
