package main.java.utils;

import main.java.models.Employee;
import main.java.models.QueryForHoliday;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryWrapper {

    private static SessionFactoryWrapper sessionFactoryWrapper;
    private SessionFactory sessionFactory;

    private SessionFactoryWrapper(){ }

    public static SessionFactoryWrapper getInstance(){
        sessionFactoryWrapper = new SessionFactoryWrapper();
        return sessionFactoryWrapper;
    }

    public  SessionFactory getSessionFactory(){
        try {
                     sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(QueryForHoliday.class)
                    .buildSessionFactory();
        }catch (Throwable e){
            System.err.println("SessionFactory have been not innitialized: " + e);
        } return sessionFactory;
    }
}
