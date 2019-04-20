package com.hg.seckill.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillGoods {
    private Long id;

    private BigDecimal seckillPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;

    private String md5;

    private Integer version;

    private Goods goods;
}