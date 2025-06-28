package com.example.sabingsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SabingsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SabingsappApplication.class, args);
	}

}
