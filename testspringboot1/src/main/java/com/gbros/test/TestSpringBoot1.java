package com.gbros.test;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
@ComponentScan("com.gbros")
@EnableAutoConfiguration
//对你创建的这个Java应用进行一些基本的配置
public class TestSpringBoot1 {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TestSpringBoot1.class);
		app.setWebEnvironment(true);
		app.setShowBanner(false);
		Set<Object> set = new HashSet<Object>();
		app.setSources(set);
		app.run(args);
	}
}
