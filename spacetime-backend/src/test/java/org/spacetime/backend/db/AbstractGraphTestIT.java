package org.spacetime.backend.db;

import org.neo4j.ogm.session.Session;
import org.spacetime.backend.utils.Neo4JQueryFactory;
import org.spacetime.backend.utils.Neo4JSessionFactory;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

/**
 * Base of test classes hierarchy, with test definitions for all crud
 * operations, and for constructors.
 *
 * Created by zua on 26/10/16.
 */
public abstract class AbstractGraphTestIT {

    private Neo4JSessionFactory factory = Neo4JSessionFactory.getInstance();

    public Session getNeo4JSession() {
        return factory.getNeo4JSession();
    }


    @BeforeMethod
    /**
     * Clean the database
     */
    public void setUp() {
        cleanDatabase();
    }

    private void cleanDatabase() {
        getNeo4JSession().query(Neo4JQueryFactory.cleanDB(), new HashMap<>());
    }


}
