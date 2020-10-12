package com.practice.bean;

import org.springframework.stereotype.Controller;

@Controller("spring")
public class SpringSkill implements SkillType {

	@Override
	public String setSkill(String skillName) {
		return skillName+"_Developer";
	}

}
