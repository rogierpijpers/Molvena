package com.capgemini.web.molvenolakeresort;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.capgemini.service"})
public class MolvenolakeresortApplication {
	public static void main(String[] args) {
		SpringApplication.run(MolvenolakeresortApplication.class, args);
	}
}
