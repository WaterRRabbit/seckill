package com.hg.seckill.limiter;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.hg.seckill.result.CodeMessageEnum;
import com.hg.seckill.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by YE
 * 2019-03-30 17:01
 */

public class RateLimitInterceptor extends HandlerInterceptorAdapter {

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);
    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if(perFilter()){
            return true;
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(new Result(CodeMessageEnum.ACCESS_LIMIT_REACHED)));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer!=null)
                writer.close();
        }
        return false;
    }

    private boolean perFilter(){
        if(!RATE_LIMITER.tryAcquire()){
            LOGGER.info("限流中...");
            return false;
        }
        return true;
    }
}
