package com.hg.seckill.dao;

import com.hg.seckill.model.OrderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderInfoMapperTest {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Test
    public void insertSelective() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(100000L);
        orderInfo.setGoodsId(100000L);
        orderInfo.setStatus((byte) 0);
        orderInfoMapper.insertSelective(orderInfo);
        System.out.println(orderInfo.getId());
    }
}