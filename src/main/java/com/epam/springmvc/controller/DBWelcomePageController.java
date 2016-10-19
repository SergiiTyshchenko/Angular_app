package com.epam.springmvc.controller;


import com.epam.springmvc.dao.RequestDao;
import com.epam.springmvc.model.Request;
import com.epam.springmvc.service.DBRequestStats;
import com.epam.springmvc.service.RequestService;
import org.hsqldb.lib.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DBWelcomePageController {

	@Autowired
	RequestService requestService;  //Service which will do all data retrieval/manipulation work

	private static final Logger logger = LoggerFactory.getLogger(DBWelcomePageController.class);

	@Autowired
	RequestDao requestDao;
	
	@RequestMapping(value = "/DB/", method = RequestMethod.GET)
	public String welcome(Model model) {

		logger.debug("epam_sergii");

		List<Request> request = requestDao.findByName("Sergii");
		
		List<Request> requests = requestDao.findAll();

		System.out.println(requests);

		model.addAttribute("request", requests);
		
		return "welcome";

	}

	//-------------------Retrieve All Requests--------------------------------------------------------

	@RequestMapping(value = "/DB/request/", method = RequestMethod.GET)
	public ResponseEntity<List<Request>> listAllDBRequests() {
		List<Request> requests = requestService.populateRequestsFromDB();
		if(requests.isEmpty()){
			return new ResponseEntity<List<Request>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Request>>(requests, HttpStatus.OK);
	}

	//-------------------Retrieve All Requests--------------------------------------------------------

	@RequestMapping(value = "/DB/request/stats", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> DBRequestsStats() {
		List<Request> requests = requestService.populateRequestsFromDB();
		Integer requests_count;
		if(requests.isEmpty()){
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		else {
			DBRequestStats statsTest = new DBRequestStats();
			Map<String,Object> stats = new java.util.HashMap<>();
			requests_count = statsTest.DBRequestCount(requests);
			stats.put("DB Request count: ", requests_count);
			List<String> request_count_per_requestor = new ArrayList<String>();
			request_count_per_requestor=statsTest.RequestsPerRequestor(requests);
			stats.put("DB Request count per requestor: ",request_count_per_requestor);
			return new ResponseEntity<Map<String,Object>>(stats, HttpStatus.OK);
		}
	}

}
