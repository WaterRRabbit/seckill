package com.hg.seckill.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.hg.seckill.dao.SeckillGoodsMapper;
import com.hg.seckill.dao.SeckillOrderMapper;
import com.hg.seckill.model.SeckillGoods;
import com.hg.seckill.model.SeckillOrder;
import com.hg.seckill.redis.RedisClient;
import com.hg.seckill.redis.RedisKeysPrefix;
import com.hg.seckill.service.SeckillService;
import com.hg.seckill.util.SerializerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * Created by YE
 * 2019-04-19 21:48
 */
@Component
public class RabbitReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitReceiver.class);
    @Resource
    private SeckillGoodsMapper seckillGoodsMapper;
    @Resource
    private RedisClient redisClient;
    @Resource
    private SeckillOrderMapper seckillOrderMapper;
    @Resource
    private SeckillService seckillService;

    @RabbitListener(queues = "seckill")
    @RabbitHandler
    public void onOrderMessage(byte[] bytes) {
        SeckillMessage message = SerializerUtil.deserializer(bytes, SeckillMessage.class);
        LOGGER.info(JSON.toJSONString(message));
        SeckillGoods seckillGoods = seckillGoodsMapper.selectByGoodsId(message.getGoodsId());
        if (seckillGoods.getStockCount() < 0) {
            redisClient.setnx((RedisKeysPrefix.IS_OVER + message.getGoodsId()).getBytes());
            return;
        }
        SeckillOrder order = seckillOrderMapper.selectByUserIdGoodsId(
                message.getUserId(), message.getGoodsId());
        if (order != null)
            return;
        seckillService.execute(message.getUserId(), seckillGoods);
    }
}
