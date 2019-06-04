package com.springboot.chapter5jpa.repository;

import com.springboot.chapter5jpa.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
        userRepository.save(new User("test1", 22));
        userRepository.save(new User("test2", 18));
    }

    @Test
    public void findByName() throws Exception {
        logger.debug(userRepository.findByName("test1").toString());
    }

    @Test
    public void findByNameAndAge() throws Exception {
        logger.debug(userRepository.findByNameAndAge("test1", 22).toString());
        logger.debug("Users:{}", userRepository.findAll().toString());
        logger.debug("Users Count:{}", userRepository.count() + "");
    }

    @Test
    public void findByName2() throws Exception {
        System.out.println(userRepository.findUser("test1"));
    }


    @Test
    public void findNames() throws Exception {
        System.out.println(userRepository.findNames(22));
    }
}