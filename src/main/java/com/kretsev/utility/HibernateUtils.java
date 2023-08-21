package com.kretsev.utility;

import com.kretsev.model.Event;
import com.kretsev.model.File;
import com.kretsev.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private final static String USER_GRAPH = "user-entity-graph";
    private final static String EVENT_GRAPH = "event-entity-graph";
    private final static String FETCH_GRAPH_PATH = "jakarta.persistence.fetchgraph";
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(File.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Event.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
