package com.backend.poll_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PollWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollWebsiteApplication.class, args);
	}

}