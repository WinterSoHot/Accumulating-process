package com.spring.ioc.Part3iocmore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gudongxian on 2017/3/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConditionConfig.class})
public class ConditionTest {

    @Autowired
    User user;

    @Test
    public void hasName() {
        Assert.assertNotNull(user);
    }
}
