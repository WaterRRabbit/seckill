package com.hg.seckill.util;

import com.alibaba.fastjson.JSON;
import com.hg.seckill.model.SeckillGoods;
import com.hg.seckill.model.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class SerializerUtilTest {

    @Test
    public void serializer() {
        User user = new User();
        user.setId(1L);
        user.setNickname("name");
        user.setPassword("123");
        user.setHead("123");
        user.setSalt("123");
        user.setRegisterDate(new Date());
        user.setLastLoginDate(new Date());
        user.setLoginCount(1);
        byte[] byes = SerializerUtil.serializer(user);
        System.out.println(Arrays.toString(byes));
        User user1 = SerializerUtil.deserializer(byes, User.class);
        System.out.println(JSON.toJSONString(user1));
    }
}