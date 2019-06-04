package com.springboot.mongodb;

import com.springboot.mongodb.domain.User;
import com.springboot.mongodb.domain.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter8mongodbApplicationTests {

	@Autowired
	UserRepository userRepository;

    @Test
	public void test() {

		userRepository.save(new User(1L,"gu",22));
		userRepository.save(new User(2L,"gu2",22));
		userRepository.save(new User(3L,"gu3",22));

		Assert.assertEquals(3,userRepository.findAll().size());

        User u = userRepository.findOne(1L);
        userRepository.delete(u);
        Assert.assertEquals(2,userRepository.findAll().size());

        User u2 = userRepository.findByName("gu2");
        userRepository.delete(u2);
        Assert.assertEquals(1,userRepository.findAll().size());

        System.out.println(userRepository.findAll());
    }

}
