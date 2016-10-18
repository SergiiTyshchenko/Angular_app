package com.epam.springmvc;


import com.epam.springmvc.dao.RequestDao;
import com.epam.springmvc.dao.RequestDaoImpl;
import com.epam.springmvc.model.Request;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class RequestDaoTest {

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
    public void testFindByname() {
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    	RequestDaoImpl requestDao = new RequestDaoImpl();
		requestDao.setNamedParameterJdbcTemplate(template);
    	
    	Request request = requestDao.findByName("Sergii");
  
    	Assert.assertNotNull(request);
    	Assert.assertEquals(1, request.getId());
    	Assert.assertEquals("Sergii", request.getRequestor());
    	Assert.assertEquals("Open", request.getStatus());

    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}