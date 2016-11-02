package org.spacetime.backend.utils;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.spacetime.backend.db.Entity;

/**
 * Created by zua on 26/10/16.
 */
public class Neo4JSessionFactory {

    private final static SessionFactory sessionFactory = new SessionFactory(getConfiguration(), Entity.class.getPackage().getName());

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.driverConfiguration()
                .setURI("http://localhost:7474")
                .setCredentials("neo4j", "unicidade")
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver");
        return configuration;
    }

    private static Neo4JSessionFactory instance = new Neo4JSessionFactory();

    public static Neo4JSessionFactory getInstance() {
        return instance;
    }

    public Session getNeo4JSession() {
        return sessionFactory.openSession();
    }

}
