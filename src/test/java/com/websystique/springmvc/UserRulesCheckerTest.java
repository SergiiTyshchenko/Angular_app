package com.websystique.springmvc;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserRulesChecker;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserRulesCheckerTest {
    @Test
    public void shouldCheckIfUserIsSuperuser() throws Exception {
        // Test class
        UserRulesChecker checker = new UserRulesChecker();
        User testuser = new User();
        testuser.setUsername("Sergii");
        checker.superUserCheck(testuser);
        // Method to check
        assertEquals("Should return country", "Ukraine", testuser.getAddress());
    }

}