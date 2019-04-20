package com.hg.seckill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by YE
 * 2019-04-17 23:48
 */
@Component
public class RedisClient {

    @Autowired
    private JedisPool jedisPool;

    public void set(byte[] key, byte[] value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (seconds < 0) {
                jedis.set(key, value);
            } else {
                jedis.setex(key, seconds, value);
            }
        } finally {
            returnJedis(jedis);
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis = null;
        byte[] bytes;
        try {
            jedis = jedisPool.getResource();
            bytes = jedis.get(key);
        } finally {
            returnJedis(jedis);
        }
        return bytes;
    }

    private void returnJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
