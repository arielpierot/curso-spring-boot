package com.github.cursospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.github.cursospringboot")
@EnableMongoRepositories
public class CursoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringBootApplication.class, args);
	}

}
