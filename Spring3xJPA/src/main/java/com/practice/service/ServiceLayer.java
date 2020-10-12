package com.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.practice.bean.Organization;
import com.practice.dao.PersistenceLayer;

@Service("organizationService")
public class ServiceLayer {

	@Autowired
	@Qualifier("organizationDao")
	PersistenceLayer persistenceLayer;
	
	
	public String addOrganization(Organization organization) {
		System.out.println("ServiceLayer.addOrganization()");
		persistenceLayer.saveOraganization(organization);
		return "Record has been added!";
	}

	
	
	public Organization getDeveloperById(int organizationId) {

		if (organizationId > 0) {
			return persistenceLayer.getOraganizationById(organizationId);
		}
		return new Organization();
	}
	
	public List<Organization> getOrganizations() {
			return persistenceLayer.getAllOraganizations();
	}

}
