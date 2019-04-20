package com.hg.seckill.dao;

import com.alibaba.fastjson.JSON;
import com.hg.seckill.model.SeckillGoods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillGoodsMapperTest {

    @Resource
    private SeckillGoodsMapper seckillGoodsMapper;

    @Test
    public void selectByPrimaryKey() {
        System.out.println(JSON.toJSONString(seckillGoodsMapper.selectByPrimaryKey(100000L)));
    }

    @Test
    public void selectAll() {
        System.out.println(JSON.toJSONString(seckillGoodsMapper.selectAll()));
    }

    @Test
    public void getVersionByGoodsId() {
        System.out.println(seckillGoodsMapper.getVersionByGoodsId(100000L));
    }

    @Test
    public void reduceStock() {
        SeckillGoods goods = seckillGoodsMapper.selectByPrimaryKey(100000L);
        seckillGoodsMapper.reduceStock(goods);
    }
}