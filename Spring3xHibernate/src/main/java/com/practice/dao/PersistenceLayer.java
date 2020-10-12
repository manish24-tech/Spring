package com.practice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.bean.Developer;

@Repository("developerDao")
@Transactional
public class PersistenceLayer {

	@Autowired
	HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//save developer  
	public void saveDeveloper(Developer developer){ 
		System.out.println("PersistanceLayer.saveDeveloper()"+developer.toString());
		//hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.save(developer);  
	}  
	
	//update developer  
	public void updateDeveloper(Developer developer){  
		hibernateTemplate.update(developer);  
	}  
	
	//delete developer  
	public void deleteDeveloper(Developer developer){  
		hibernateTemplate.delete(developer);  
	}  
	
	//get developer by id  
	public Developer getDeveloperById(int developerId){
		Developer developer = hibernateTemplate.get(Developer.class, developerId);
	    return developer;  
	}
	
	//get list of developers  
	public List<Developer> getDevelopers(){  
	    List<Developer> developerList = hibernateTemplate.loadAll(Developer.class);
	    return developerList;  
	}  
}
