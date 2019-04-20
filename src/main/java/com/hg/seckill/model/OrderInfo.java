package com.hg.seckill.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderInfo {
    private Long id;

    private Long userId;

    private Long goodsId;

    private Long deliveryAddrId;

    private String goodsName;

    private Integer goodsCount;

    private BigDecimal goodsPrice;

    private Byte orderChannel;

    private Byte status;

    private Date createDate;

    private Date payDate;
}