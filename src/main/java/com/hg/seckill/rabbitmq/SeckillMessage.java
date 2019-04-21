package com.hg.seckill.rabbitmq;

import lombok.Data;

/**
 * Created by YE
 * 2019-04-21 15:31
 */
@Data
public class SeckillMessage {

    private Long userId;
    private Long goodsId;
}
