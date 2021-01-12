package com.Restaurant.RestaurantAccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
//@CrossOrigin(origins="http://172.20.10.15:3001")
public class RestaurantTableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantTableServiceApplication.class, args);
	}

}
