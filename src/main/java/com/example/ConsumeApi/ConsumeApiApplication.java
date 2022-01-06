package com.example.ConsumeApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumeApiApplication.class, args);
	}

}
