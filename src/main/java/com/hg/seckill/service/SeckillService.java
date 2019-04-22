package com.hg.seckill.service;

import com.hg.seckill.dao.SeckillOrderMapper;
import com.hg.seckill.model.SeckillGoods;
import com.hg.seckill.model.SeckillOrder;
import com.hg.seckill.rabbitmq.RabbitSender;
import com.hg.seckill.rabbitmq.SeckillMessage;
import com.hg.seckill.redis.RedisClient;
import com.hg.seckill.redis.RedisKeysPrefix;
import com.hg.seckill.result.CodeMessageEnum;
import com.hg.seckill.result.Expose;
import com.hg.seckill.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by YE
 * 2019-04-20 15:54
 */
@Service
public class SeckillService {

    @Resource
    private RedisClient redisClient;
    @Resource
    private RabbitSender rabbitSender;
    @Resource
    private SeckillOrderMapper seckillOrderMapper;
    @Resource
    private SeckillGoodsService seckillGoodsService;
    @Resource
    private OrderService orderService;

    public Result seckill(Long userId, Long goodsId) {
        //判断重复秒杀
        Long isKill = redisClient.setnx((RedisKeysPrefix.IS_KILL + goodsId).getBytes());
        if (isKill == 0)
            return new Result(CodeMessageEnum.SECKILL_REPEATE);
        Long stock = redisClient.decr((RedisKeysPrefix.STOCK + goodsId).getBytes());
        if (stock < 0)
            return new Result(CodeMessageEnum.SECKILL_OVER);

        SeckillMessage message = new SeckillMessage();
        message.setUserId(userId);
        message.setGoodsId(goodsId);
        rabbitSender.send(message);
        return new Result(CodeMessageEnum.SECKILL_WAIT);
    }

    public Long getResult(Long userId, Long goodsId) {
        SeckillOrder order = seckillOrderMapper.selectByUserIdGoodsId(userId, goodsId);
        if (order != null)
            return order.getOrderId();
        boolean isOver = redisClient.exists((RedisKeysPrefix.IS_OVER + goodsId).getBytes());
        if (isOver) {
            return -1L;
        }
        return 0L;
    }

    public void execute(Long userId, SeckillGoods seckillGoods) {
        boolean success = seckillGoodsService.reduceStock(seckillGoods);
        if (success) {
            orderService.createOrder(userId, seckillGoods);
        } else {
            //减库存失败
            redisClient.setnx((RedisKeysPrefix.IS_OVER +
                    seckillGoods.getGoods().getId()).getBytes());
        }
    }

    public Expose expose(Long goodsId) {
        SeckillGoods seckillGoods = seckillGoodsService.selectByGoodsId(goodsId);
        Expose exp = new Expose();
        exp.setGoodsId(goodsId);
        exp.setStartTime(seckillGoods.getStartDate());
        exp.setEndTime(seckillGoods.getEndDate());
        Date now = new Date();
        exp.setNowTime(now);
        if (now.getTime()<seckillGoods.getStartDate().getTime()
                || now.getTime() > seckillGoods.getEndDate().getTime()) {
            exp.setStart(false);
        }else{
            exp.setStart(true);
        }
        return exp;
    }
}
