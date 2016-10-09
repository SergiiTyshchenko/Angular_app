package com.websystique.springmvc.service;


import com.websystique.springmvc.model.Request;

public class RequestRulesChecker {

    public Request superRequestCheck(Request request){
        if (request.getRequestor().equals("Sergii") || request.getRequestor().equals("Stas") ){
            request.setAssignee(request.getRequestor());
        }
        return request;
    }

}
