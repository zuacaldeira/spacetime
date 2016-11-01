package org.spacetime.backend.db.math;

import org.spacetime.backend.db.Neo4JQueryFactory;
import org.spacetime.backend.db.Neo4JSessionFactory;
import org.spacetime.backend.db.math.operations_alternative.AlternativeMultiplicationRelationship;
import org.spacetime.backend.db.math.utils.DatabaseUtils;

import java.util.HashMap;

/**
 * Created by zua on 01/11/16.
 */
public class RecursiveDatabaseEvolutiveBuilder {
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

    private static void addRelations(int j, int i) {
        DatabaseUtils.getNeo4JSession().save(new AlternativeMultiplicationRelationship(memory(j), memory(i), memory(j*i)));
    }

    private static NumberNode memory(int i) {
        if(!numbers.containsKey(i)) {
            numbers.put(i, new NumberNode(i));
        }
        return numbers.get(i);
    }

}
