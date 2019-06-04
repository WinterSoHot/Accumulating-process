package com.springboot.redisandcache;

import com.springboot.redisandcache.domain.User;
import com.springboot.redisandcache.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter17redisandcacheApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		userRepository.save(new User("AAA",12));
		userRepository.save(new User("BBB",22));
	}

	@Test
	public void contextLoads() {

		User u1 = userRepository.findByName("AAA");
		System.out.println("第一次查询：" + u1.getAge());

		User u2 = userRepository.findByName("AAA");
		System.out.println("第二次查询：" + u2.getAge());

		u1.setAge(20);
		userRepository.save(u1);
		User u3 = userRepository.findByName("AAA");
		System.out.println("第三次查询：" + u3.getAge());

		User u4 = userRepository.findByName("BBB");
        System.out.println("第四次查询：" + u4.getAge());

    }

}
