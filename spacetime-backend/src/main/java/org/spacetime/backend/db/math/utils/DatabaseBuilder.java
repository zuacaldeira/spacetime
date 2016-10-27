package org.spacetime.backend.db.math.utils;

import org.neo4j.ogm.session.Session;
import org.spacetime.backend.db.Entity;
import org.spacetime.backend.db.Neo4JQueryFactory;
import org.spacetime.backend.db.Neo4JSessionFactory;
import org.spacetime.backend.db.math.NumberNode;
import org.spacetime.backend.db.math.PredecessorRelationship;
import org.spacetime.backend.db.math.operations.AdditionRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeAdditionRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeDivisionRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeMultiplicationRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeSubtractionRelationship;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zua on 27/10/16.
 */
public class DatabaseBuilder  {

    private static Neo4JSessionFactory factory = Neo4JSessionFactory.getInstance();

    private static Session getNeo4JSession() {
        return factory.getNeo4JSession();
    }

    public static void build(int size) {

        Map<Integer, NumberNode> numberMap = initNumberMap(size);

        for(int i = 0; i <= size; i++) {
            NumberNode a = numberMap.get(i);
            for (int j = 0; j <= size; j++) {
                NumberNode b = numberMap.get(j);
                if(i+j <= size) {
                    NumberNode c1 = numberMap.get(i + j);
                    create(new AlternativeAdditionRelationship(a, b, c1));
                    create(new AlternativeSubtractionRelationship(c1, b, a));
                }
                if(i*j <=size) {
                    NumberNode c2 = numberMap.get(i * j);
                    create(new AlternativeMultiplicationRelationship(a, b, c2));
                    if (j != 0) {
                        create(new AlternativeDivisionRelationship(c2, b, a));
                    }
                }
                System.out.print(i + ", " + j + "\r");
                System.out.flush();
            }
            System.out.println();
        }
    }

    private static Map<Integer, NumberNode> initNumberMap(int max) {
        Map<Integer, NumberNode> numbers = new HashMap<>();
        NumberNode last = null;
        for(int i = 0; i <= max; i++) {
            NumberNode current = new NumberNode(i);
            numbers.put(i, current);
            if(i != 0) {
                create(new PredecessorRelationship(last, current));
            }
            last = current;
            System.out.print(i + "\r");
            System.out.flush();
        }
        System.out.println();
        return numbers;
    }


    public static boolean create(Entity entity) {
        if(entity instanceof AlternativeAdditionRelationship) {
            AlternativeAdditionRelationship addition = (AlternativeAdditionRelationship) entity;
            getNeo4JSession().save(addition);

            getNeo4JSession().queryForObject(
                    AdditionRelationship.class,
                    Neo4JQueryFactory.getAlternativeAdditionRelationship(addition),
                    new HashMap());
            return true;
        }
        else if(entity instanceof AlternativeSubtractionRelationship) {
            AlternativeSubtractionRelationship subtraction = (AlternativeSubtractionRelationship) entity;
            getNeo4JSession().save(subtraction);

            getNeo4JSession().queryForObject(
                    AdditionRelationship.class,
                    Neo4JQueryFactory.getAlternativeSubtractionRelationship(subtraction),
                    new HashMap());
            return true;
        }
        else if(entity instanceof AlternativeMultiplicationRelationship) {
            AlternativeMultiplicationRelationship multiplication = (AlternativeMultiplicationRelationship) entity;
            getNeo4JSession().save(multiplication);

            getNeo4JSession().queryForObject(
                    AdditionRelationship.class,
                    Neo4JQueryFactory.getAlternativeMultiplicationRelationship(multiplication),
                    new HashMap());
            return true;
        }
        else if(entity instanceof AlternativeDivisionRelationship) {
            AlternativeDivisionRelationship division = (AlternativeDivisionRelationship) entity;
            getNeo4JSession().save(division);

            getNeo4JSession().queryForObject(
                    AdditionRelationship.class,
                    Neo4JQueryFactory.getAlternativeDivisionRelationship(division),
                    new HashMap());
            return true;
        }
        else if(entity instanceof PredecessorRelationship) {
            PredecessorRelationship predecessor = (PredecessorRelationship) entity;
            getNeo4JSession().save(predecessor);

            getNeo4JSession().queryForObject(
                    PredecessorRelationship.class,
                    Neo4JQueryFactory.getPredecessorRelationship(predecessor),
                    new HashMap());
            return true;
        }
        return false;
    }



}
