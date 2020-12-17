package com.Restaurant.RestaurantLoginService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class RestaurantLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantLoginServiceApplication.class, args);
	}

}
