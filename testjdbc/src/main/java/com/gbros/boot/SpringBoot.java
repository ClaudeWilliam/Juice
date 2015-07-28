package com.gbros.boot;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("com.gbros")
public class SpringBoot {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBoot.class);
		app.setWebEnvironment(true);
		app.setShowBanner(false);
		Set<Object> set = new HashSet<Object>();
		app.setSources(set);
		app.run(args);
	}
}
