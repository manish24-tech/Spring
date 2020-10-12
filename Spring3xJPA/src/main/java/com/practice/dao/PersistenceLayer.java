package com.practice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.bean.Organization;

@Repository("organizationDao")
@Transactional
public class PersistenceLayer {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void saveOraganization(Organization organization) {
		entityManager.persist(organization);	
	}
	
	public Organization getOraganizationById(int organizationId) {
		return entityManager.find(Organization.class, organizationId);	
	}
	
	@SuppressWarnings("unchecked")
	public List<Organization> getAllOraganizations() {
		System.out.println("PersistenceLayer.getAllOraganizations()");
		Query createQuery = entityManager.createQuery("select org from Organization org");
		System.out.println(createQuery.getResultList());
		return 	createQuery.getResultList();
	}

}
