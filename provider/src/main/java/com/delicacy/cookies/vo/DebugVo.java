package com.delicacy.cookies.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author linzhenghui
 * @date 2020/11/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebugVo {


    @Min(value = -1, message = "age必填，不能为0")
    private Integer age;
    @NotNull(message = "姓名不能为null")
    private String name;


}
