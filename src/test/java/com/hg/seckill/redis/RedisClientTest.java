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
        redisClient.set((RedisKeysPrefix.STOCK+100000L).getBytes(), "6".getBytes(),-1);
    }

    @Test
    public void get() {
        System.out.println(new String(redisClient.get("key".getBytes())));
    }

    @Test
    public void delete() {
    }

    @Test
    public void exists() {
    }

    @Test
    public void incr() {
        System.out.println(redisClient.incr((RedisKeysPrefix.STOCK+100000L).getBytes()));
    }

    @Test
    public void decr() {
        System.out.println(redisClient.decr((RedisKeysPrefix.STOCK+100000L).getBytes()));
    }

    @Test
    public void setnx() {
        System.out.println(redisClient.setnx("exists".getBytes()));
    }
}