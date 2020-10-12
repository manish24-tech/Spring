package com.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.practice.bean.Developer;
import com.practice.dao.PersistenceLayer;

@Service("developerService")
public class ServiceLayer {

	@Autowired
	@Qualifier("developerDao")
	private PersistenceLayer persistanceLayer;

	public String addDeveloper(Developer developer) {
		System.out.println("ServiceLayer.addDeveloper()");
		persistanceLayer.saveDeveloper(developer);
		return "Record has been added!";
	}

	public String updateDeveloper(Developer developer) {
		try {
			persistanceLayer.updateDeveloper(developer);
			return "Developer Has Been Updated Successfully!";
		} catch (Exception e) {
			return "ServiceLayer.updateDeveloper() : Error while update developer " + e.getMessage();
		}
	}

	public String deleteDeveloper(Developer developer) {
		try {
			persistanceLayer.deleteDeveloper(developer);
			return "Developer Has Been Deleted Successfully!";
		} catch (Exception e) {
			return "ServiceLayer.deleteDeveloper() : Error while delete developer " + e.getMessage();
		}
	}

	public Developer getDeveloperById(int developerId) {

		if (developerId > 0) {
			return persistanceLayer.getDeveloperById(developerId);
		}
		return new Developer();
	}

	public List<Developer> getAllDeveloper() {
		try {
			List<Developer> developers = persistanceLayer.getDevelopers();
			return developers;
		} catch(Exception e) {
			return new ArrayList<>(); 
		}
	}
}
