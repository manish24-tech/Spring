package com.practice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.practice.bean.Developer;

@Configuration
@ComponentScan(basePackages="com.practice")
public class ApplicationConfiguration {
	
	
	@Bean(name="developer")
	public Developer getDeveloper() {
		return new Developer();
	}

}
