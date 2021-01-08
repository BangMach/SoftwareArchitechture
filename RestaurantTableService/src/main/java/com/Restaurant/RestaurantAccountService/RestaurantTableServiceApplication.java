package com.Restaurant.RestaurantAccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class RestaurantTableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantTableServiceApplication.class, args);
	}

}
