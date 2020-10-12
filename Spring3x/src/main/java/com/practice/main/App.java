package com.practice.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.bean.Application;
import com.practice.configuration.AppConfiguration;

/**
 * This is main class from where context object instruct to spring IOC for bean
 * registration
 * 
 * @author manish.luste
 */
public class App {
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext factory = new AnnotationConfigApplicationContext(AppConfiguration.class);
		
		System.out.println("App.main() : Initiate Process ");
		Application beanFactory = (Application) factory.getBean("application");
		beanFactory.setAppId(1);
		beanFactory.setAppName("Spring 3.x");
		beanFactory.displayApplicationData();
		
		System.out.println("App.main() : End Process ");
		
		
	}
}
