//entry point for springboot project

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@SpringBootApplication annotation is a combination of @Configuration, @EnableConfiguration and @ComponentScan
//@SpringBootApplication tells that this psvm is main class of spring boot application.

@SpringBootApplication
//@ComponentScan("com.example.*")
@ComponentScan({"com.example.controller", "com.example.service", "com.example.aop"})

@EntityScan("com.example.entity")   //this annotation will scan all the entity classes.

@EnableJpaRepositories("com.example.repository") 
@EnableSwagger2    //URL to access Swagger UI - http://localhost:8082/swagger-ui.html#/
@EnableScheduling

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
