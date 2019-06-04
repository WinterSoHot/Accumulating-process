package com.springboot.jpa.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Before
    @Rollback
    public void setUp() throws Exception {
        userRepository.save(new User("gg", 22));
        userRepository.save(new User("gg1", 22));
        userRepository.save(new User("gg2", 22));
        userRepository.save(new User("gg3", 22));
        userRepository.save(new User("gg4", 22));
    }

    @Test
    public void find() throws Exception {
        System.out.println(userRepository.findAll());
    }

    @Test
    public void findByName() throws Exception {
        System.out.println(userRepository.findByName("gg"));
    }

    @Test
    public void findNameAndAge() throws Exception {
        System.out.println(userRepository.findByNameAndAge("gg1",22));
    }

    @Test
    public void findName() throws Exception {
        System.out.println(userRepository.findName("gg3"));
    }

    @After
    @Rollback
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }
}