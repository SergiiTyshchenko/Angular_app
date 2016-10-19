package com.epam.springmvc.dao;

import com.epam.springmvc.model.Request;


import java.util.List;

public interface RequestDao {

	List<Request> findByName(String requestor);
	
	List<Request> findAll();

}