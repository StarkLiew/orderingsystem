package com.example.orderingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class OrderingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderingSystemApplication.class, args);
	}

}
