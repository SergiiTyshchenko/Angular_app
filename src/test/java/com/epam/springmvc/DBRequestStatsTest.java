package com.epam.springmvc;

import com.epam.springmvc.dao.RequestDao;
import com.epam.springmvc.dao.RequestDaoImpl;
import com.epam.springmvc.model.Request;
import com.epam.springmvc.service.DBRequestStats;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class DBRequestStatsTest {

    private EmbeddedDatabase db;

    RequestDao requestDao;



    @Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql/create-db.sql")
                .addScript("sql/insert-data.sql")
                .build();


    }
    @Test
    public void shouldReturnDBRequestCount() throws Exception {
        // Test class
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
        RequestDaoImpl requestDao = new RequestDaoImpl();
        requestDao.setNamedParameterJdbcTemplate(template);
        DBRequestStats statsTest = new DBRequestStats();
        List<Request> requests = requestDao.findAll();
        Integer result = 10;
        // Method to check: DBRequestCount
        assertEquals("Should return requests count", result, statsTest.DBRequestCount(requests));
    }

    @Test
    public void shouldReturnDBRequestsCountPerRequestor() throws Exception {
        // Test class
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
        RequestDaoImpl requestDao = new RequestDaoImpl();
        requestDao.setNamedParameterJdbcTemplate(template);
        DBRequestStats statsTest = new DBRequestStats();
        List<Request> requests = requestDao.findAll();
        List<String> stats = new ArrayList<String>();
        stats.add(0,"Sergii Requests: 5");
        stats.add(1,"Stas Requests: 5");
        // Method to check: RequestsPerRequestor
        assertEquals("Should return requests count per requestor", stats, statsTest.RequestsPerRequestor(requests));
    }
}