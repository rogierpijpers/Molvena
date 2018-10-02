package com.capgemini.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.capgemini.service", "com.capgemini.data", "com.capgemini.web.util"})
@EnableJpaRepositories("com.capgemini.data")
@EntityScan( basePackages = {"com.capgemini.domain"} )
public class MolvenolakeresortApplication {
	public static void main(String[] args) {
		SpringApplication.run(MolvenolakeresortApplication.class, args);
	}
}
