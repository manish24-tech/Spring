package com.practice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.practice.bean.Application;

/**
 * This is replacement of xml configuration file according spring 3.0 
 * it is act as metadata file which read by spring ioc for dependecy injection
 * @author manish.luste
 */
@Configuration
@ComponentScan(basePackages="com.practice")
public class AppConfiguration {
	
	@Bean(name="application")
	public Application getApplicationBean() {
		return new Application();
	}
}
