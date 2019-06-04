package com.springboot.chapter7jpaconf;

import com.springboot.chapter7jpaconf.domain.a.User;
import com.springboot.chapter7jpaconf.domain.a.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter7jpaconfApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void contextLoads() {
	    userRepository.save(new User("gu",22));
        System.out.println(userRepository.findAll());
    }

}
