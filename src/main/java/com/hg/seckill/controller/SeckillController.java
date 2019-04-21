package com.hg.seckill.controller;

import com.hg.seckill.result.CodeMessageEnum;
import com.hg.seckill.result.Result;
import com.hg.seckill.service.SeckillService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;


/**
 * Created by YE
 * 2019-04-17 21:44
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Resource
    private SeckillService seckillService;

    @RequestMapping(value = "/{goodsId}/execution")
    @ResponseBody
    public Result execution(@PathVariable("goodsId") Long goodsId) {
        Long userId = Long.valueOf((String) SecurityUtils.getSubject().getPrincipal());
        if (userId == null)
            return new Result(CodeMessageEnum.NOT_LOGIN);
        return seckillService.seckill(userId, goodsId);
    }

    @RequestMapping(value = "/{goodsId}/result")
    @ResponseBody
    public Result result(@PathVariable("goodsId") Long goodsId) {
        Long userId = Long.valueOf((String) SecurityUtils.getSubject().getPrincipal());
        if (userId == null)
            return new Result(CodeMessageEnum.NOT_LOGIN);
        Long res = seckillService.getResult(userId, goodsId);
        if (res.equals(0L))
            return new Result(CodeMessageEnum.SECKILL_WAIT);
        if (res.equals(-1L))
            return new Result(CodeMessageEnum.SECKILL_OVER);
        return new Result(CodeMessageEnum.SUCCESS);
    }

}
