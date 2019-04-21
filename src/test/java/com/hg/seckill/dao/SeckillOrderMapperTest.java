package com.hg.seckill.dao;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillOrderMapperTest {

    @Resource
    private SeckillOrderMapper seckillOrderMapper;

    @Test
    public void selectByUserIdGoodsId() {
        System.out.println(JSON.toJSONString(seckillOrderMapper.
                selectByUserIdGoodsId(100000L, 100000L)));
    }
}