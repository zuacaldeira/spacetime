package org.spacetime.backend.db.math;

import org.spacetime.backend.db.AbstractGraphTestIT;
import org.spacetime.backend.db.Entity;
import org.spacetime.backend.db.entities.NumberNode;
import org.spacetime.backend.db.relationships.Addition;
import org.spacetime.backend.utils.OldDatabaseBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 26/10/16.
 */
public class OGMDatabaseTestIT extends AbstractGraphTestIT {

    @Test
    public void constructEntityTest() {
        Addition relationship = new Addition(
                new NumberNode(1),
                new NumberNode(2),
                new NumberNode(3));
        assertNotNull(relationship.getOperands());
        assertNotNull(relationship.getResult());
    }

    @Test(dataProvider = "additions")
    public void create(Entity entity) {
        assertTrue(OldDatabaseBuilder.create(entity));
    }

    @Test
    public void create100Operations() {
        int size = 10;
        OldDatabaseBuilder.build(size);
    }


    @DataProvider(name = "additions")
    public Object[][] additions() {
        return new Object[][]{
                {new Addition(
                        new NumberNode(1),
                        new NumberNode(2),
                        new NumberNode(3))}
        };
    }
}
