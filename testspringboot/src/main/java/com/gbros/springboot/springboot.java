package com.gbros.springboot;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.gbros")
@EnableAutoConfiguration
public class springboot {
	

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(springboot.class);
		app.setWebEnvironment(true);
		app.setShowBanner(false);
		Set<Object> set = new HashSet<Object>();
		app.setSources(set);
		app.run(args);
	}

}
