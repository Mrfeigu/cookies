package com.delicacy.cookies.factory.single;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 交通工具基类
 * @author feigu
 * @date 2020/10/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseVehicle {

    private String name;

    public abstract void use();

}
