package com.practice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.LinkedList;

import com.practice.bean.Developer;

@Service("developerService")
public class PersistenceLayer {

	public List<Developer> getDevelopers() {
		Developer developer1 = new Developer();
		developer1.setId(1);
		developer1.setName("Dipak");
		
		Developer developer2 = new Developer();
		developer2.setId(2);
		developer2.setName("Nehal");
		
		
		Developer developer3 = new Developer();
		developer3.setId(3);
		developer3.setName("Manish");
		
		List<Developer> developerList = new LinkedList<Developer>();
		developerList.add(developer1);
		developerList.add(developer2);
		developerList.add(developer3);
		
		return developerList;
	}
	
}
