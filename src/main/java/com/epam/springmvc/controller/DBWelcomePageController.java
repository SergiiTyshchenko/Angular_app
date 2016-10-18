package com.epam.springmvc.controller;


import com.epam.springmvc.dao.RequestDao;
import com.epam.springmvc.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DBWelcomePageController {

	private static final Logger logger = LoggerFactory.getLogger(DBWelcomePageController.class);

	@Autowired
	RequestDao requestDao;
	
	@RequestMapping(value = "/DB", method = RequestMethod.GET)
	public String welcome(Model model) {

		logger.debug("epam_sergii");

		//Request requests = userDao.findByName("epam_sergii");
		
		List<Request> requests = requestDao.findAll();

		System.out.println(requests);

		model.addAttribute("request", requests);
		
		return "welcome";

	}


}