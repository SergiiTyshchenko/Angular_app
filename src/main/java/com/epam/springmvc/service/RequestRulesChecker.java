package com.epam.springmvc.service;


import com.epam.springmvc.model.Request;

public class RequestRulesChecker {

    public Request superRequestCheck(Request request){
        if (request.getRequestor().equals("Sergii") || request.getRequestor().equals("Stas") ){
            request.setAssignee(request.getRequestor());
        }
        return request;
    }

}
