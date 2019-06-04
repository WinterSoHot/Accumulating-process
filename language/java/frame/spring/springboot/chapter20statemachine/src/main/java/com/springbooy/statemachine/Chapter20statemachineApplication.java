package com.springbooy.statemachine;

import com.springbooy.statemachine.config.Events;
import com.springbooy.statemachine.config.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

//在项目服务启动的时候就去加载一些数据或做一些事情 CommandLineRunner 也可以@Order 加载顺序
@SpringBootApplication
public class Chapter20statemachineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Chapter20statemachineApplication.class, args);
    }


    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }
}
