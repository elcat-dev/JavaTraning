package com.ertc.taskman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ertc.taskman.repositories"})
@EntityScan(basePackages = {"com.ertc.taskman.entities"})
public class TaskmanApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskmanApplication.class, args);
	}
}
