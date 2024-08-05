package com.gabriel.pive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiveApplication.class, args);
	}

}
