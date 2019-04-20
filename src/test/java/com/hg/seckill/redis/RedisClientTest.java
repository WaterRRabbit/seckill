package com.hg.seckill.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisClientTest {

    @Resource
    private RedisClient redisClient;

    @Test
    public void set() {
        redisClient.set("key".getBytes(), "jedis".getBytes(), 200);
    }

    @Test
    public void get() {
        System.out.println(new String(redisClient.get("key".getBytes())));
    }
}