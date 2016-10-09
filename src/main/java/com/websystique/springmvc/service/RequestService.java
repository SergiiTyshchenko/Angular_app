package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Request;


public interface RequestService {

    Request findById(long id);

    Request findByRequestor(String requestor);

    void saveRequest(Request request);

    void updateRequest(Request request);

    void deleteRequestById(long id);

    List<Request> findAllRequests();

    void deleteAllRequests();

    public boolean isRequestExist(Request request);

}