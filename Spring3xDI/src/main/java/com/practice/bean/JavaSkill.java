package com.practice.bean;

import org.springframework.stereotype.Controller;

@Controller("java")
public class JavaSkill implements SkillType {

	@Override
	public String setSkill(String skillName) {
		return skillName+"_Developer";
	}

}
