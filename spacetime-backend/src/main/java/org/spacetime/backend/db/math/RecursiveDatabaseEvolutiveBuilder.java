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

    public static void evolve(int n) {
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.loadFromNumbersQuery(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createSuccessors(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createOperands(), new HashMap<>());
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.createMultiplications(), new HashMap<>());
    }

    private static void evolve(int n, int i) {
        if(i <= n) {
            evolve(n-1);
            for(int j = i; j < n; j++) {
                addRelations(j, i);
            }
        }
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
