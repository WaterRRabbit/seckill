package com.hg.seckill.controller;

import com.hg.seckill.result.CodeMessageEnum;
import com.hg.seckill.result.Result;
import com.hg.seckill.service.SeckillGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * Created by YE
 * 2019-04-17 21:44
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Resource
    private SeckillGoodsService seckillGoodsService;

    @RequestMapping(value = "/{id}/execution")
    @ResponseBody
    public Result execution(@PathVariable("id") Long id){

        return new Result(CodeMessageEnum.SUCCESS);
    }

}
