package com.springboot.chapter7jpaconf;

import com.springboot.chapter7jpaconf.domain.b.Message;
import com.springboot.chapter7jpaconf.domain.b.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SecondConfigTest {

    @Autowired
    MessageRepository messageRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test() throws Exception {
        messageRepository.save(new Message(400,"400error"));
        System.out.println(messageRepository.findByCode(400));
    }
}