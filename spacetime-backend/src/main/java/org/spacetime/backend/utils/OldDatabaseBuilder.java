package org.spacetime.backend.utils;

import org.spacetime.backend.db.Entity;
import org.spacetime.backend.db.entities.NumberNode;
import org.spacetime.backend.db.relationships.*;
import org.spacetime.backend.db.relationships.Successor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zua on 27/10/16.
 */
public class OldDatabaseBuilder {

    public static void main(String... args){
        DatabaseEvolutiveBuilder.evolve(10000);
        evolve(10000);
    }

    private static void evolve(int generation) {

    }

    public static void build(int size) {

        Map<Integer, NumberNode> numberMap = initNumberMap(size);

        for(int i = 0; i <= size; i++) {
            NumberNode a = numberMap.get(i);
            for (int j = 0; j <= size; j++) {
                NumberNode b = numberMap.get(j);
                if(i+j <= size) {
                    NumberNode c1 = numberMap.get(i + j);
                    create(new Addition(a, b, c1));
                    create(new Subtraction(c1, b, a));
                }
                if(i*j <=size) {
                    NumberNode c2 = numberMap.get(i * j);
                    create(new Multiplication(a, b, c2));
                    if (j != 0) {
                        create(new Division(c2, b, a));
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
                create(new Successor(last, current));
            }
            last = current;
            System.out.print(i + "\r");
            System.out.flush();
        }
        System.out.println();
        return numbers;
    }


    public static boolean create(Entity entity) {
        if(entity instanceof Addition) {
            Addition addition = (Addition) entity;
            DatabaseUtils.getNeo4JSession().save(addition);
            return true;
        }
        else if(entity instanceof Subtraction) {
            Subtraction subtraction = (Subtraction) entity;
            DatabaseUtils.getNeo4JSession().save(subtraction);
            return true;
        }
        else if(entity instanceof Multiplication) {
            Multiplication multiplication = (Multiplication) entity;
            DatabaseUtils.getNeo4JSession().save(multiplication);
            return true;
        }
        else if(entity instanceof Division) {
            Division division = (Division) entity;
            DatabaseUtils.getNeo4JSession().save(division);
            return true;
        }
        else if(entity instanceof Successor) {
            Successor predecessor = (Successor) entity;
            DatabaseUtils.getNeo4JSession().save(predecessor);
            return true;
        }
        return false;
    }



}
