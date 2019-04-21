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

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public boolean delete(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long ret = jedis.del(key);
            return ret > 0;
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 自增一
     *
     * @param key
     * @return
     */
    public Long incr(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 自减一
     *
     * @param key
     * @return
     */
    public Long decr(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally {
            returnJedis(jedis);
        }
    }

    public Long setnx(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setnx(key, "tmp".getBytes());
        } finally {
            returnJedis(jedis);
        }
    }

    private void returnJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
