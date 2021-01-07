package com.BangMach.RestaurantUserService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import javax.ws.rs.core.Application;

@SpringBootTest(classes = Application.class)
class RestaurantUserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantUserServiceApplicationTests.class, args);
	}

}
