package org.spacetime.backend.main;

import org.spacetime.backend.db.Neo4JQueryFactory;
import org.spacetime.backend.db.RelationshipEntity;
import org.spacetime.backend.db.math.IsPrime;
import org.spacetime.backend.db.math.NumberNode;
import org.spacetime.backend.db.math.PredecessorRelationship;
import org.spacetime.backend.db.math.PrimeNode;
import org.spacetime.backend.db.math.operations_alternative.*;
import org.spacetime.backend.db.math.utils.DatabaseUtils;

import java.util.*;

/**
 * Created by zua on 31/10/16.
 */
public class DatabaseEvolutiveBuilder {


    private static HashMap<Integer, NumberNode> memory = new HashMap<Integer, NumberNode>();
    private static HashMap<NumberNode, RelationshipEntity> predecessors = new HashMap<>();
    private static HashMap<Operands, RelationshipEntity> additions = new HashMap<>();
    private static HashMap<Operands, RelationshipEntity> subtractions = new HashMap<>();
    private static HashMap<Operands, RelationshipEntity> multiplications = new HashMap<>();
    private static HashMap<Operands, RelationshipEntity> divisions = new HashMap<>();
    private static int GENERATION = 0;
    private static AbstractSequentialList primes = new LinkedList();


    public static void main(String...args) {
        deleteDatabase();
        evolve(10);
    }

    private static void deleteDatabase() {
        DatabaseUtils.getNeo4JSession().query(Neo4JQueryFactory.cleanDB(), new HashMap<>());
    }


    public static void evolve(int n) {
        GENERATION = n;
        addSuccessors();
        //evolveAddition();
        evolveMultiplication();
        //evolveSubtraction();
        //evolveDivision();
        extend();
    }

    private static void addSuccessors() {
        System.out.println("Generation DB SUCC");
        for(int i = 1; i <= GENERATION; i++) {
            if (i < GENERATION) {
                addSuccessor(i + 1, i);
            }
        }
    }

    private static void evolveDivision() {
        System.out.println("Generation DB /");
        for(int i = 1; i <= GENERATION; i++) {
            for(int j = 1; j <= GENERATION; j++) {
                addDivision(i, j);
                if( j >= i) {
                    break;
                }
            }
        }
    }

    private static void evolveSubtraction() {
        System.out.println("Generation DB -");
        for(int i = 1; i <= GENERATION; i++) {
            for(int j = 1; j <= GENERATION; j++) {
                addSubtraction(i, j);
                if(i-j <= 0) {
                    break;
                }
            }
        }
    }


    public static void evolveMultiplication() {
        System.out.println("Generation DB x");
        for(int i = 1; i <= GENERATION; i++) {
            for(int j = 1; j <= GENERATION; j++) {
                addMultiplication(i, j);
                if(i*j >= GENERATION) {
                    break;
                }
            }
        }
    }

    public static void evolveAddition() {
        System.out.println("Generation DB + ");
        for(int i = 1; i <= GENERATION; i++) {
            for(int j = 1; j <= GENERATION; j++) {
                addAddition(i, j);
                if(i+j >= GENERATION) {
                    break;
                }
            }
        }
    }


    public static void extend() {
        System.out.println("Extending DB...");
        markPrimes();
    }

    private static void markPrimes() {
        PrimeNode pn = new PrimeNode();
        Map<NumberNode, Integer> multiplicationsPerNumber = transform(multiplications);
        multiplicationsPerNumber.keySet().forEach(
                m -> {
                    if(multiplicationsPerNumber.get(m) == 2) {
                        addRelationToPrimeNumber(m);
                    }
                });
    }

    private static Map<NumberNode, Integer> transform(HashMap<Operands, RelationshipEntity> multiplications) {
        Map<NumberNode, Integer> result = new HashMap<>();
        multiplications.values().forEach(
                (m) -> {
                    AlternativeMultiplicationRelationship mm = (AlternativeMultiplicationRelationship) m;
                    if(!result.containsKey(mm.getResult())) {
                        result.put(mm.getResult(), 1);
                    }
                    else {
                        int v = result.get(mm.getResult()) + 1;
                        result.put(mm.getResult(), v);
                    }
            });
        return result;
    }

    private static void relations(int n, int i) {
        addAddition(n, i);
        addSubtraction(n, i);
        addMultiplication(n, i);
        addDivision(n, i);
    }


    private static void addNode(int k) {
        DatabaseUtils.getNeo4JSession().save(memory(k));
    }

    private static NumberNode memory(int k) {
        if(!memory.containsKey(k)) {
            memory.put(k, new NumberNode(k));
        }

        return memory.get(k);
    }

    private static void addSuccessor(int successor, int predecessor) {
        if(predecessor >= 0) {
            DatabaseUtils.getNeo4JSession().save(memory(new PredecessorRelationship(memory(predecessor), memory(successor))));
        }
    }

    private static PredecessorRelationship memory(PredecessorRelationship predecessor) {
        if(!predecessors.containsKey(predecessor.getPredecessor())) {
            predecessors.put(predecessor.getPredecessor(), predecessor);
        }
        return (PredecessorRelationship) predecessors.get(predecessor.getPredecessor());
    }


    private static AlternativeOperationRelationship memory(AlternativeAdditionRelationship operation) {
        Operands key = operation.getOperands();
        if(!additions.containsKey(key)) {
            additions.put(key, operation);
        }
        return (AlternativeOperationRelationship) additions.get(key);
    }

    private static AlternativeOperationRelationship memory(AlternativeSubtractionRelationship operation) {
        Operands key = operation.getOperands();
        if(!subtractions.containsKey(key)) {
            subtractions.put(key, operation);
        }
        return (AlternativeOperationRelationship) subtractions.get(key);
    }

    private static AlternativeOperationRelationship memory(AlternativeMultiplicationRelationship operation) {
        Operands key = operation.getOperands();
        if(!multiplications.containsKey(key)) {
            multiplications.put(key, operation);
        }
        return (AlternativeOperationRelationship) multiplications.get(key);
    }

    private static AlternativeOperationRelationship memory(AlternativeDivisionRelationship operation) {
        Operands key = operation.getOperands();
        if(!divisions.containsKey(key)) {
            divisions.put(key, operation);
        }
        return (AlternativeOperationRelationship) divisions.get(key);
    }


    private static void addAddition(int left, int right) {
        if(left + right <= GENERATION) {
            DatabaseUtils.getNeo4JSession().save(memory(new AlternativeAdditionRelationship(memory(left), memory(right), memory(left+right))));
        }
    }

    private static void addSubtraction(int left, int right) {
        if(left - right >= 0) {
            DatabaseUtils.getNeo4JSession().save(memory(new AlternativeSubtractionRelationship(memory(left), memory(right), memory(left-right))));
        }
    }

    private static void addMultiplication(int left, int right) {
        if(left * right <= GENERATION) {
            DatabaseUtils.getNeo4JSession().save(memory(new AlternativeMultiplicationRelationship(memory(left), memory(right), memory(left*right))));
        }
    }

    private static void addDivision(int divisor, int divider) {
        if(divider != 0 && divisor%divider == 0) {
            DatabaseUtils.getNeo4JSession().save(memory(new AlternativeDivisionRelationship(memory(divisor), memory(divider), memory(divisor/divider))));
        }
    }

    private static void addRelationToPrimeNumber(NumberNode prime) {
        System.out.println("Saving prime " + prime);
        DatabaseUtils.getNeo4JSession().save(new IsPrime(prime, new PrimeNode()));
    }


}
