package com.naumen.films.application.model.data.utils;

import com.naumen.films.application.model.data.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.url}")
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.username}")
    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.password}")
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;

    public HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Film.class);
                configuration.addAnnotatedClass(Genre.class);
                configuration.addAnnotatedClass(Review.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Role.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Something went wrong!\n" + e);
            }
        }
        return sessionFactory;
    }
}
