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
@DessertTaskType(type = "CookieFilter", order = 9)
public class CookieFilter extends AbstractTaskFilter {


    @Override
    public void doFilter(BaseTaskWrapper<?> taskWrapper, List<Object> resList) {
        System.out.println("CookieFilter");
        resList.add("CookieFilter");
    }


}
