package com.BangMach.RestaurantUserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class RestaurantUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantUserServiceApplication.class, args);
	}

}
