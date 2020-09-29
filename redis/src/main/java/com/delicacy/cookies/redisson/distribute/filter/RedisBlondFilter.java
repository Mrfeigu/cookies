package com.delicacy.cookies.redisson.distribute.filter;

import com.delicacy.cookies.redisson.entity.Hero;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 布隆过滤器原理 https://zhuanlan.zhihu.com/p/43263751
 * redis实现布隆过滤器 https://juejin.im/post/6844903694782185480
 *
 * @author linzhenghui
 * @date 2020/9/29
 */
@Slf4j
@Component
public class RedisBlondFilter {

    @Resource
    private RedissonClient redisson;

    public Object filter(){
        RBloomFilter<Object> bloomFilter = redisson.getBloomFilter("bloomFilter");
        // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add(new Hero(1, "崔斯特", "卡牌大师"));
        bloomFilter.add(new Hero(2, "锐雯", "放逐之刃"));
        return bloomFilter.contains(new Hero(1, "崔斯特", "卡牌大师"));
    }


}
