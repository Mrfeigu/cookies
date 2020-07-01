package com.delicacy.cookies.aware.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linzhenghui
 * @date 2020/7/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userName;
    private int age;

}
