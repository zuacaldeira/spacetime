package org.spacetime.backend.db;

import org.neo4j.ogm.session.Session;
import org.spacetime.backend.db.Neo4JSessionFactory;
import org.spacetime.backend.db.NodeEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Base of test classes hierarchy, with test definitions for all crud
 * operations, and for constructors.
 *
 * Created by zua on 26/10/16.
 */
public abstract class AbstractEntityTestIT {

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


    /**
     * Tests node constructors, ensuring nodes are properly instantiated.
     */
    @Test
    public abstract void constructEntityTest();

    /**
     * Test storage of a node in the underlying database
     */
    @Test
    public abstract void create(Entity entity);

    /**
     * Tests the existence of a previously stored node.
     * @return The node entity
     */
    @Test
    public abstract NodeEntity read();

    /**
     * Tests nodes are correctly updated.
     */
    @Test
    public abstract void update();

    /**
     * Tests nodes are correctly removed from the database.
     */
    @Test
    public abstract void delete();


}
