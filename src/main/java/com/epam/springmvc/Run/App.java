package com.epam.springmvc.Run;

import com.epam.springmvc.dao.MySQLRequestDao;
import com.epam.springmvc.dao.RequestDao;
import com.epam.springmvc.model.Request;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("hello-dispatcher-servlet.xml");

        MySQLRequestDao mySQLRequestDao= (MySQLRequestDao) context.getBean("mysqlrequestDao");
        Request request = new Request(1, "Sergii", "teыt mysql", "","","",1);
        mySQLRequestDao.insert(request);

        Request request1 = mySQLRequestDao.findByRequestId(1);
        System.out.println(request1);

    }
}