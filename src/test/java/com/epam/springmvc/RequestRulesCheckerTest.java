package com.epam.springmvc;

import com.epam.springmvc.model.Request;
import com.epam.springmvc.service.RequestRulesChecker;
import org.junit.Test;

import static org.junit.Assert.*;


public class RequestRulesCheckerTest {
    @Test
    public void shouldCheckIfRequestIsSuperrequest() throws Exception {
        // Test class
        RequestRulesChecker checker = new RequestRulesChecker();
        Request testrequest = new Request();
        testrequest.setRequestor("Sergii");
        checker.superRequestCheck(testrequest);
        // Method to check
        assertEquals("Should return current requestor", testrequest.getRequestor(), testrequest.getAssignee());
    }

}