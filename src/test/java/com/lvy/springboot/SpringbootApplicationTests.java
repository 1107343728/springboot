package com.lvy.springboot;

import com.lvy.springboot.entity.User;
import com.lvy.springboot.repository.UserRepository;
import com.lvy.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	@Test
	public void contextLoads() {
	}

	@Test
	public void test1() {
		List<User> userList = userService.findByNameLike("小明");
		System.out.println(userList);
	}

	@Test
	public void test2() {
		User user = userRepository.findUser("小明");
		System.out.println(user);
	}

	@Test
	public void test3() {
		User user = userRepository.findByAgeGreaterThan(11);
		System.out.println(user);
	}
}
