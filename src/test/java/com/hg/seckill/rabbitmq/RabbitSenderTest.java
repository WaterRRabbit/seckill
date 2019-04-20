package com.hg.seckill.rabbitmq;

import com.hg.seckill.model.SeckillOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitSenderTest {
    @Resource
    private RabbitSender sender;

    @Test
    public void send() {
        SeckillOrder order = new SeckillOrder();
        order.setId(100000L);
        order.setGoodsId(100000L);
        order.setOrderId(100000L);
        order.setUserId(100000L);
        sender.send(order);
    }
}