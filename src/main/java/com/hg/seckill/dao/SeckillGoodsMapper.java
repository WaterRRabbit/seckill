package com.hg.seckill.dao;

import com.hg.seckill.model.SeckillGoods;

import java.util.List;

public interface SeckillGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SeckillGoods record);

    int insertSelective(SeckillGoods record);

    SeckillGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SeckillGoods record);

    int updateByPrimaryKey(SeckillGoods record);

    List<SeckillGoods> selectAll();

    Integer getVersionByGoodsId(Long id);

    int reduceStock(SeckillGoods seckillGoods);
}