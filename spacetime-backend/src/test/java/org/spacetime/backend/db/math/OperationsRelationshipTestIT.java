package org.spacetime.backend.db.math;

import org.spacetime.backend.db.*;
import org.spacetime.backend.db.math.operations.AdditionRelationship;
import org.spacetime.backend.db.math.operations.DivisionRelationship;
import org.spacetime.backend.db.math.operations.MultiplicationRelationship;
import org.spacetime.backend.db.math.operations.SubtractionRelationship;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 26/10/16.
 */
public class OperationsRelationshipTestIT extends AbstractEntityTestIT {
    @Override
    public void constructEntityTest() {
        AdditionRelationship relationship = new AdditionRelationship(
                new NumberNode(1),
                new NumberNode(2),
                new NumberNode(3));
        assertNotNull(relationship.getLeft());
        assertNotNull(relationship.getRight());
        assertNotNull(relationship.getResult());
    }

    @Override
    @Test(dataProvider = "additions")
    public void create(Entity entity) {
        if(entity instanceof AdditionRelationship) {
            AdditionRelationship addition = (AdditionRelationship) entity;
            getNeo4JSession().save(addition);

            getNeo4JSession().queryForObject(
                    AdditionRelationship.class,
                    Neo4JQueryFactory.getAdditionRelationship(addition),
                    new HashMap());
        }
        else if(entity instanceof SubtractionRelationship) {
            SubtractionRelationship subtraction = (SubtractionRelationship) entity;
            getNeo4JSession().save(subtraction);

            getNeo4JSession().queryForObject(
                    AdditionRelationship.class,
                    Neo4JQueryFactory.getSubtractionRelationship(subtraction),
                    new HashMap());
        }
        else if(entity instanceof MultiplicationRelationship) {
            MultiplicationRelationship multiplication = (MultiplicationRelationship) entity;
            getNeo4JSession().save(multiplication);

            getNeo4JSession().queryForObject(
                    AdditionRelationship.class,
                    Neo4JQueryFactory.getMultiplicationRelationship(multiplication),
                    new HashMap());
        }
        else if(entity instanceof DivisionRelationship) {
            DivisionRelationship division = (DivisionRelationship) entity;
            getNeo4JSession().save(division);

            getNeo4JSession().queryForObject(
                    AdditionRelationship.class,
                    Neo4JQueryFactory.getDivisionRelationship(division),
                    new HashMap());
        }
        else if(entity instanceof PredecessorRelationship) {
            PredecessorRelationship predecessor = (PredecessorRelationship) entity;
            getNeo4JSession().save(predecessor);

            getNeo4JSession().queryForObject(
                    PredecessorRelationship.class,
                    Neo4JQueryFactory.getPredecessorRelationship(predecessor),
                    new HashMap());
        }
    }

    @Test
    public void create100Additions() {
        int size = 100;
        Map<Integer, NumberNode> numberMap = initNumberMap(size);

        for(int i = 0; i < size; i++) {
            NumberNode a = numberMap.get(i);
            for(int j = 0; j < size; j++) {
                NumberNode b = numberMap.get(j);
                if(i+j <= size) {
                    NumberNode c = numberMap.get(i+j);
                    create(new AdditionRelationship(a, b, c));
                    create(new SubtractionRelationship(c, b, a));
                }
                if(i*j <= size) {
                    NumberNode c = numberMap.get(i*j);
                    create(new MultiplicationRelationship(a, b, c));
                    if(j != 0) {
                        create(new DivisionRelationship(c, b, a));
                    }
                }
                else {
                    break;
                }
            }
        }
    }

    private Map<Integer, NumberNode> initNumberMap(int max) {
        Map<Integer, NumberNode> numbers = new HashMap<>();
        for(int i = 0; i <= max; i++) {
            numbers.put(i, new NumberNode(i));
        }
        return numbers;
    }

    @Override
    public NodeEntity read() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }


    @DataProvider(name = "additions")
    public Object[][] additions() {
        return new Object[][]{
                {new AdditionRelationship(
                        new NumberNode(1),
                        new NumberNode(2),
                        new NumberNode(3))}
        };
    }
}
