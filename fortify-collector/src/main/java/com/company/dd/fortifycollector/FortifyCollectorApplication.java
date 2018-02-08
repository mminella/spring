package com.company.dd.fortifycollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@EnableTask
@SpringBootApplication
public class FortifyCollectorApplication {

	public static void main(String[] args)  {
		SpringApplication.run(FortifyCollectorApplication.class, args);
	}
}
