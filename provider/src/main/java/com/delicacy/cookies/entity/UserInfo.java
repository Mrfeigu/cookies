package com.delicacy.cookies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linzhenghui
 * @date 2020/7/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Integer age;
    private String name;
    public Object msg;
}
