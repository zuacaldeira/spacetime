package org.spacetime.backend.db.math.utils;

import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;
import org.spacetime.backend.db.Neo4JQueryFactory;
import org.spacetime.backend.db.Neo4JSessionFactory;
import org.spacetime.backend.db.math.operations_alternative.*;

import java.util.*;

/**
 * Created by zua on 27/10/16.
 */
public class DatabaseUtils {

    public static Neo4JSessionFactory factory = Neo4JSessionFactory.getInstance();

    public  static Session getNeo4JSession() {
        return factory.getNeo4JSession();
    }


    public static <T extends AlternativeOperationRelationship> List<T> loadProblems(Class<T> operationRelationshipClass) {
        System.out.println("\t\tLoading... " + operationRelationshipClass);
        LinkedList<T> list = new LinkedList<>(getNeo4JSession().loadAll(operationRelationshipClass, 5));
        System.out.println("\t\tLoaded " + list.size() + " operations.");
        return list;
    }

    public static List loadAllProblems() {
        Collection<AlternativeAdditionRelationship> dataA = getNeo4JSession().loadAll(AlternativeAdditionRelationship.class);
        Collection<AlternativeSubtractionRelationship> dataS = getNeo4JSession().loadAll(AlternativeSubtractionRelationship.class);
        Collection<AlternativeMultiplicationRelationship> dataM = getNeo4JSession().loadAll(AlternativeMultiplicationRelationship.class);
        Collection<AlternativeDivisionRelationship> dataD = getNeo4JSession().loadAll(AlternativeDivisionRelationship.class);

        Collection data = dataA;
        data.addAll(dataS);
        data.addAll(dataM);
        data.addAll(dataD);

        if(data instanceof List) {
            return (List) data;
        }
        return new ArrayList<>(data);
    }
}
