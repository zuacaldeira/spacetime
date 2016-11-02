package org.spacetime.backend.db.math;

import org.spacetime.backend.db.*;
import org.spacetime.backend.db.entities.NumberNode;
import org.spacetime.backend.utils.ChypherDatabaseBuilder;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 26/10/16.
 */
public class OperationsRelationshipTestIT extends AbstractGraphTestIT {

    @Test
    public void createConnectedDatabase() {
        long before = System.currentTimeMillis();
        ChypherDatabaseBuilder.evolveConnected(10);

        long after = System.currentTimeMillis();
        System.out.println("Total Time: " + (after-before)/1000 + " seconds");
    }

    @Test
    public void createDisconnectedDatabase() {
        // Before
        long before = System.currentTimeMillis();
        ChypherDatabaseBuilder.evolveDisconnected(10);

        // After
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
}
