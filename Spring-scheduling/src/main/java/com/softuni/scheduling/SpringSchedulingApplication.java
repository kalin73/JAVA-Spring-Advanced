package com.softuni.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringSchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSchedulingApplication.class, args);
	}

}
