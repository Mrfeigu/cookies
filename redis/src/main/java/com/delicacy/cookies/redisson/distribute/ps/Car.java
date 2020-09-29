package com.delicacy.cookies.redisson.distribute.ps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author feigu
 * @date 2020/09/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car  implements Serializable {

    private static final long serialVersionUID = -1L;

    private double price;
    private String colour;

}