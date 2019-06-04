package com.spring.ioc.Part4aop;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gudongxian on 2017/3/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class,Config2.class})
public class Test {
    @Autowired
    Perform perform;

    @Autowired
    BlackDisc blackDisc;

    @Autowired
    ArgsAspect aspect;

    @Autowired
    DefaultPerform defaultPerform;

    @org.junit.Test
    public void testMain() {
//        perform.perform();
//        perform.Around();

//        blackDisc.play(1);
//        blackDisc.play(1);
//        blackDisc.play(2);
//        blackDisc.play(3);
//
//        Assert.assertEquals(2,aspect.getPlayCount(1));
//        Assert.assertEquals(1,aspect.getPlayCount(2));
//        Assert.assertEquals(1,aspect.getPlayCount(3));
    }
}
