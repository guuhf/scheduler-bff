package com.guuh.scheduler_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SchedulerBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerBffApplication.class, args);
	}

}
