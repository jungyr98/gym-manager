package com.potato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.potato", "com.potato.config"})
public class GymManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymManagerBackendApplication.class, args);
	}

}
