package com.springboot.cache;

import com.springboot.cache.domain.User;
import com.springboot.cache.domain.UserRepository;
import net.sf.ehcache.CacheManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter16cacheApplicationTests {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private CacheManager cacheManager;

	@Before
	public void setUp() throws Exception {
		userRepository.save(new User("AAA",10));
		userRepository.save(new User("ABCDEFGHASsdDSD",22));
	}

	@Test
	public void contextLoads() {
		User u1 = userRepository.findByName("AAA");
		System.out.println("第一次查询："+u1.getAge());

		User u2 = userRepository.findByName("AAA");
		System.out.println("第二次查询："+u2.getAge());

		u1.setAge(20);
		userRepository.save(u2);

		User u3 = userRepository.findByName("AAA");
		System.out.println("第三次查询："+u3.getAge());

        System.out.println(userRepository.findByName("ABCDEFGHASsdDSD"));;
        System.out.println(userRepository.findByName("ABCDEFGHASsdDSD"));;
	}

}
