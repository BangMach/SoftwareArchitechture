package com.BangMach.RestaurantMenuService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class RestaurantMenuServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantMenuServiceApplication.class, args);
	}

}
