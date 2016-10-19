package com.epam.springmvc.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MainInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        //return new Class[] { MainConfiguration.class };
        return new Class[] { SpringRootConfiguration.class };
    }

    @Override
    //protected Class<?>[] getServletConfigClasses() {return null;}
    protected Class<?>[] getServletConfigClasses() {return new Class[] { MainConfiguration.class };}

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter() };
        return singleton;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter("spring.profiles.active", "hsql");
    }
}



