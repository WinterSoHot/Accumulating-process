package com.springboot.mysqlmapper.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @Rollback
    public void insert() throws Exception {

        userMapper.insert("gu", 22);
        User gu = userMapper.findByName("gu");
        Assert.assertEquals(22, gu.getAge().intValue());
    }

    @Test
    public void findByName() throws Exception {

        User gu = userMapper.findByName("gudongxian");
        System.out.println(gu);
    }


    @Test
    public void findAll() throws Exception {

        List<User> all = userMapper.findAll();
        System.out.println(all);
    }

    @Test
    @Rollback
    public void update() throws Exception {
        User user = new User();
        user.setName("gudongxian");
        user.setAge(18);
        userMapper.update(user);

        User u = userMapper.findByName("gudongxian");
        System.out.println(u);
    }

    @Test
    public void delete() throws Exception {
        userMapper.delete(3L);
        System.out.println(userMapper.findAll());
    }

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "mapname");
        map.put("age", 124);
        userMapper.insertByMap(map);
        System.out.println(userMapper.findAll());
    }
}