package com.hg.seckill.controller;

import com.hg.seckill.model.SeckillGoods;
import com.hg.seckill.service.SeckillGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by YE
 * 2019-04-18 11:20
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private SeckillGoodsService seckillGoodsService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public String list(Map<String, Object> map) {
        map.put("seckillGoodsList", seckillGoodsService.selectAll());
        return "seckill";
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Map<String, Object> map) {
        if(id==null){
            return "redirect:/goods/list";
        }
        SeckillGoods seckillGoods = seckillGoodsService.selectByPrimaryKey(id);
        if (seckillGoods==null){
            return "redirect:/goods/list";
        }
        map.put("seckillGoods", seckillGoods);
        return "detail";
    }
}
