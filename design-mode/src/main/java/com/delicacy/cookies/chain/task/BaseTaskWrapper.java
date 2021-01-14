package com.delicacy.cookies.chain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linzhenghui
 * @date 2021/1/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseTaskWrapper<T> {

    /** 主责*/
    private String first;

    /** 任务*/
    private T task;

}
