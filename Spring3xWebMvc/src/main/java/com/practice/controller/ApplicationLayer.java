package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.practice.service.PersistenceLayer;

@Controller
@RequestMapping("/")
public class ApplicationLayer {
	
	@Autowired
	@Qualifier("developerService")
	private PersistenceLayer persistanceLayer;
	
	@RequestMapping("view")
	public ModelAndView viewRecord() {
		ModelAndView model = new ModelAndView();
		model.addObject("developerList", persistanceLayer.getDevelopers());
		model.setViewName("viewRecords");
		return model;
	}

}
