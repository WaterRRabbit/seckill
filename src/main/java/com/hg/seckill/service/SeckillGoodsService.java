package com.hg.seckill.service;

import com.hg.seckill.dao.SeckillGoodsMapper;
import com.hg.seckill.model.SeckillGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by YE
 * 2019-04-18 14:32
 */
@Service
public class SeckillGoodsService {

    @Resource
    private SeckillGoodsMapper seckillGoodsMapper;

    public List<SeckillGoods> selectAll() {
        return seckillGoodsMapper.selectAll();
    }

    public SeckillGoods selectByPrimaryKey(Long id) {
        return seckillGoodsMapper.selectByPrimaryKey(id);
    }

    public boolean isOver(Long goodsId) {

        return false;
    }

    /**
     * 减库存，通过乐观锁实现
     *
     * @param seckillGoods
     * @return
     */
    public boolean reduceStock(SeckillGoods seckillGoods) {
        int count = 0;
        int tmp;
        do {
            count++;
            seckillGoods.setVersion(seckillGoodsMapper.getVersionByGoodsId(
                    seckillGoods.getGoods().getId()));
            tmp = seckillGoodsMapper.reduceStock(seckillGoods);
            if (tmp > 0)
                break;
        } while (count <= 5);
        return tmp > 0;
    }
}
