package org.spacetime.backend.db.math;

import org.spacetime.backend.db.*;
import org.spacetime.backend.db.math.operations_old.OldAdditionRelationshipOld;
import org.spacetime.backend.main.DatabaseEvolutiveBuilder;
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
        OldAdditionRelationshipOld relationship = new OldAdditionRelationshipOld(
                new NumberNode(1),
                new NumberNode(2),
                new NumberNode(3));
        assertNotNull(relationship.getLeft());
        assertNotNull(relationship.getRight());
        assertNotNull(relationship.getResult());
    }

    public void create(Entity entity) {
    }

    @Test
    public void createDatabase() {
        long before = System.currentTimeMillis();
        DatabaseEvolutiveBuilder.evolve(10000);

        long after = System.currentTimeMillis();
        System.out.println("Total Time: " + (after-before)/1000 + " seconds");
    }

    @Test
    public void createDatabaseRec() {
        long before = System.currentTimeMillis();
        RecursiveDatabaseEvolutiveBuilder.evolveDisconnected(10);

        long after = System.currentTimeMillis();
        System.out.println("Total Time: " + (after-before)/1000 + " seconds");
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
                {new OldAdditionRelationshipOld(
                        new NumberNode(1),
                        new NumberNode(2),
                        new NumberNode(3))}
        };
    }
}
