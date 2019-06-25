package com.example.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages= {"com.example.bms" , "com.bms.database" , "com.bms.service" , "com.bms.dto" , "com.bms.controller" , "com.bms.configuration"})
@EntityScan(basePackages= {"com.bms.module"})
@SpringBootApplication
public class BmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsApplication.class, args);
	}

}
