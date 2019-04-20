package com.hg.seckill.rabbitmq;

import com.hg.seckill.model.SeckillOrder;
import com.hg.seckill.util.SerializerUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by YE
 * 2019-04-19 21:01
 */
@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(SeckillOrder seckillOrder) {

        rabbitTemplate.convertAndSend("seckill",
                SerializerUtil.serializer(seckillOrder));
    }
}
