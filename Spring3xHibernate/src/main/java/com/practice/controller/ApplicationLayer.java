package com.practice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.practice.bean.Developer;
import com.practice.service.ServiceLayer;

@Controller
//@RequestMapping(path ="/")
public class ApplicationLayer {
	
	@Autowired
	@Qualifier("developerService")
	private ServiceLayer serviceLayer;
	
	@GetMapping("home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}
	
	@PostMapping("addDeveloper")
	public ModelAndView addDeveloperRecord(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		String name = request.getParameter("developerName");
		Date dob = null;
		try {
			System.out.println("DATE => "+request.getParameter("developerDob"));
			
			dob = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("developerDob"));
			System.out.println("dob => "+dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Developer developer = new Developer();
		//developer.setId(1);
		developer.setName(name);
		developer.setDob(dob);
		String addDeveloperStatus = serviceLayer.addDeveloper(developer);
		model.addObject("status",addDeveloperStatus);
		
		model.setViewName("viewRecords");
		return model;
	}
	
	@GetMapping("developer")
	public ModelAndView viewRecord() {
		ModelAndView model = new ModelAndView();
		model.addObject("developerList", serviceLayer.getAllDeveloper());
		model.setViewName("viewRecords");
		return model;
	}

}
