package com.websystique.springmvc.service;


import com.websystique.springmvc.model.User;

public class UserRulesChecker {

    public User superUserCheck(User user){
        if (user.getUsername().equals("Sergii") || user.getUsername().equals("Stas") ){
            user.setAssignee(user.getUsername());
        }
        return user;
    }

}
