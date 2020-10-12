package com.practice.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.bean.Developer;
import com.practice.configuration.ApplicationConfiguration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      @SuppressWarnings("resource")
      AnnotationConfigApplicationContext factory = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
      Developer developer = (Developer) factory.getBean("developer");
      developer.getDeveloperSkill();
    }
}
