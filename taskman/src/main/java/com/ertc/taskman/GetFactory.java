package com.ertc.taskman;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetFactory {
    private static GetFactory getFactory;

    public static GetFactory getInstance(){
        synchronized (GetFactory.class) {
            if (getFactory == null) {
                getFactory = new GetFactory();
            }
        }
        return getFactory;
    }

    private SessionFactory factory;

    public SessionFactory getFactory() {
        return factory;
    }

    private GetFactory(){
        factory = new Configuration()
                .configure("config/oracle.cfg.xml")
                //.addAnnotatedClass(Task.Status.class)
                .buildSessionFactory();
    }

    public void closeFactory(){
        factory.close();
    }
}
