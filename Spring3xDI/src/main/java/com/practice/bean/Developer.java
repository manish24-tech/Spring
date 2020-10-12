package com.practice.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Developer {

	@Autowired
	@Qualifier("java")
	private JavaSkill javaSkill;

	@Autowired
	@Qualifier("spring")
	private SpringSkill springSkill;

	public void getDeveloperSkill() {
		System.out.println("Developer.getDeveloperSkill() : There is 2 Developer : ");

		System.out.println("1. " + this.javaSkill.setSkill("JAVA-8"));

		System.out.println("2. " + this.springSkill.setSkill("Spring-3.x"));

	}

}
