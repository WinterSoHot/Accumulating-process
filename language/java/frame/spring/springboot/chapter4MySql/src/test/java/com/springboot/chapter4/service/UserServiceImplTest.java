package com.springboot.chapter4.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception{
        userService.create("gudongxian",22);
    }

    @Test
    public void create() throws Exception {
        userService.create("yummy",22);
    }

    @Test
    public void deleteByName() throws Exception {
        userService.deleteByName("gudongxian");
    }

    @Test
    public void getAllUsers() throws Exception {
        Assert.assertEquals(userService.getAllUsers().intValue(),2);
    }

    @Test
    public void deleteAllUsers() throws Exception {
        userService.deleteAllUsers();
    }

}