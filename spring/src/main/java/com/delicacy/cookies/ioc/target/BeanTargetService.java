package com.delicacy.cookies.ioc.target;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/12/21
 */
@Data
@Slf4j
@Service
public class BeanTargetService {

    private String name = "BeanTargetService";
    private Integer age = 0;

}
