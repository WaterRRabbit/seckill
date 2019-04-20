package com.hg.seckill.service;

import com.hg.seckill.dao.OrderInfoMapper;
import com.hg.seckill.dao.SeckillOrderMapper;
import com.hg.seckill.model.OrderInfo;
import com.hg.seckill.model.SeckillGoods;
import com.hg.seckill.model.SeckillOrder;
import com.hg.seckill.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by YE
 * 2019-04-20 15:51
 */
@Service
public class OrderService {

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private SeckillOrderMapper seckillOrderMapper;

    public OrderInfo getById(Long id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public OrderInfo createOrder(User user, SeckillGoods seckillGoods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(user.getId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(seckillGoods.getGoods().getId());
        orderInfo.setGoodsName(seckillGoods.getGoods().getGoodsName());
        orderInfo.setGoodsPrice(seckillGoods.getSeckillPrice());
        orderInfo.setOrderChannel((byte) 1);
        orderInfo.setStatus((byte) 0);
        orderInfo.setUserId(user.getId());
        orderInfo.setCreateDate(new Date());
        orderInfoMapper.insertSelective(orderInfo);

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setGoodsId(seckillGoods.getGoods().getId());
        seckillOrderMapper.insertSelective(seckillOrder);

        return orderInfo;
    }
}
