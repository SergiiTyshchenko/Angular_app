package com.epam.springmvc.dao;

import com.epam.springmvc.model.Request;

import java.util.List;

public interface MySQLRequestDao {

	public void insert(Request request);
	public Request findByRequestId(int custId);

}