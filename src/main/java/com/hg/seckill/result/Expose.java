package com.hg.seckill.result;

import lombok.Data;

import java.util.Date;

/**
 * Created by YE
 * 2019-04-22 10:46
 */
@Data
public class Expose {

    private Long goodsId;

    private boolean isStart;

    private Date startTime;

    private Date endTime;

    private Date nowTime;

    private String md5;

}
