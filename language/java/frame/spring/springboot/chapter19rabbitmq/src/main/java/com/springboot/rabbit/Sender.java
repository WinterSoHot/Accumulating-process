package com.springboot.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author gudongxian
 * @create 2017-12-14 下午6:46
 **/
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("println: " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}
