package com.delicacy.cookies.chain.filter;

import com.delicacy.cookies.chain.annotation.DessertTaskType;
import com.delicacy.cookies.chain.task.BaseTaskWrapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author linzhenghui
 * @date 2021/1/14
 */
@Component
@DessertTaskType(type = "ChocolateFilter", order = 10)
public class ChocolateFilter extends AbstractTaskFilter {


    @Override
    public void doFilter(BaseTaskWrapper<?> taskWrapper, List<Object> resList) {
        System.out.println("ChocolateFilter");
        resList.add("ChocolateFilter");
    }


}
