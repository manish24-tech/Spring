package com.practice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.practice.bean.Organization;
import com.practice.service.ServiceLayer;

@Controller
public class ApplicationLayer {

	@Autowired
	@Qualifier("organizationService")
	ServiceLayer serviceLayer;
	
	
	@GetMapping("home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}
	
	@PostMapping("addOrganization")
	public ModelAndView addOrganizationRecord(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		String name = request.getParameter("organizationName");
		System.out.println("name => "+name);
		
		String status = serviceLayer.addOrganization(new Organization(name));
		model.addObject("status", status);
		model.setViewName("viewRecords");
		return model;
	}
	
	@GetMapping("organization")
	public ModelAndView viewRecord() {
		System.out.println("ApplicationLayer.viewRecord()");
		ModelAndView model = new ModelAndView();
		List<Organization> organization = serviceLayer.getOrganizations();
		model.addObject("organizationList", organization);
		model.setViewName("viewRecords");
		return model;
	}
	
}
