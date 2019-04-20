package com.hg.seckill.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.hg.seckill.model.SeckillOrder;
import com.hg.seckill.util.SerializerUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * Created by YE
 * 2019-04-19 21:48
 */
@Component
public class RabbitReceiver {

    @RabbitListener(queues = "seckill")
    @RabbitHandler
    public void onOrderMessage(byte[] bytes){
        SeckillOrder order = SerializerUtil.deserializer(bytes, SeckillOrder.class);
        System.out.println(JSON.toJSONString(order));
    }
}
