package org.spacetime.backend.utils;

import org.neo4j.ogm.session.Session;
import org.spacetime.backend.db.relationships.*;

import java.util.ArrayList;
import java.util.Collection;
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


    public static <T extends OperationRelationship> List<T> loadProblems(Class<T> operationRelationshipClass) {
        System.out.println("\t\tLoading... " + operationRelationshipClass);
        LinkedList<T> list = new LinkedList<>(getNeo4JSession().loadAll(operationRelationshipClass, 5));
        System.out.println("\t\tLoaded " + list.size() + " operations.");
        return list;
    }

    public static List loadAllProblems() {
        Collection<Addition> dataA = getNeo4JSession().loadAll(Addition.class);
        Collection<Subtraction> dataS = getNeo4JSession().loadAll(Subtraction.class);
        Collection<Multiplication> dataM = getNeo4JSession().loadAll(Multiplication.class);
        Collection<Division> dataD = getNeo4JSession().loadAll(Division.class);

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
