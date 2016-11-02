package org.spacetime.backend.utils;

import org.spacetime.backend.db.entities.NumberNode;

import java.util.HashMap;

/**
 * Created by zua on 01/11/16.
 */
public class ChypherDatabaseBuilder {
    private static HashMap<Integer, NumberNode> numbers = new HashMap<>();

    public static void evolveConnected(int n) {
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.deleteDatabase(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.addUniqueConstraint(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.loadFromNumbersQuery(100), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createSuccessors(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createMultiplications(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createAdditions(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createSubtractions(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createDivisions(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createPrimes(), new HashMap<>());
    }

    public static void evolveDisconnected(int n) {
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.deleteDatabase(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.addUniqueConstraint(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.loadFromNumbersQuery(100), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createSuccessors(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createMultiplicationsDisconnected(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createAdditionsDisconnected(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createSubtractionsDisconnected(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createDivisionsDisconnected(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createPrimes(), new HashMap<>());
    }

}
