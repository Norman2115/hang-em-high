package com.dsa.hangemhigh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.dsa.hangemhigh")
public class HangEmHighApplication {

	public static void main(String[] args) {
		SpringApplication.run(HangEmHighApplication.class, args);
	}

}
