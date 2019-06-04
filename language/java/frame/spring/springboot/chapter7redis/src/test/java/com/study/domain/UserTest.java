package com.study.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String,User> userRedisTemplate;

    @Test
    public void test() throws Exception {

        stringRedisTemplate.opsForValue().set("hello","123");

        Assert.assertEquals("123",stringRedisTemplate.opsForValue().get("hello"));

        // 保存对象
        User user = new User("超人", 20);
        userRedisTemplate.opsForValue().set(user.getName(), user);

        user = new User("蝙蝠侠", 30);
        userRedisTemplate.opsForValue().set(user.getName(), user);

        user = new User("蜘蛛侠", 40);
        userRedisTemplate.opsForValue().set(user.getName(), user);

        Assert.assertEquals(20, userRedisTemplate.opsForValue().get("超人").getAge().longValue());
        Assert.assertEquals(30, userRedisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
        Assert.assertEquals(40, userRedisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());

    }
}