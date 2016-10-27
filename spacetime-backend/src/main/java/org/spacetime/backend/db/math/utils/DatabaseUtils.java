package org.spacetime.backend.db.math.utils;

import org.neo4j.ogm.session.Session;
import org.spacetime.backend.db.Neo4JSessionFactory;
import org.spacetime.backend.db.math.operations.OperationRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeOperationRelationship;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 27/10/16.
 */
public class DatabaseUtils {

    public static Neo4JSessionFactory factory = Neo4JSessionFactory.getInstance();

    public  static Session getNeo4JSession() {
        return factory.getNeo4JSession();
    }


    public static <T extends AlternativeOperationRelationship> List<T> loadProblems(Class<T> operationRelationshipClass) {
        return new LinkedList<>(getNeo4JSession().loadAll(operationRelationshipClass, 5));
    }
}
