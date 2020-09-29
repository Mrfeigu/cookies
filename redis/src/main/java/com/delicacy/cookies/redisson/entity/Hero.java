package com.delicacy.cookies.redisson.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author linzhenghui
 * @date 2020/9/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hero implements Serializable {

    private Integer type;
    private String name;
    private String desc;

}
