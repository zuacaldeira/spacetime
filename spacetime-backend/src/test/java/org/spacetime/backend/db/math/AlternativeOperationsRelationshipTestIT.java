package org.spacetime.backend.db.math;

import org.spacetime.backend.db.AbstractEntityTestIT;
import org.spacetime.backend.db.Entity;
import org.spacetime.backend.db.NodeEntity;
import org.spacetime.backend.db.math.operations_alternative.AlternativeAdditionRelationship;
import org.spacetime.backend.db.math.utils.DatabaseBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 26/10/16.
 */
public class AlternativeOperationsRelationshipTestIT extends AbstractEntityTestIT {
    @Override
    public void constructEntityTest() {
        AlternativeAdditionRelationship relationship = new AlternativeAdditionRelationship(
                new NumberNode(1),
                new NumberNode(2),
                new NumberNode(3));
        assertNotNull(relationship.getOperands());
        assertNotNull(relationship.getResult());
    }

    @Override
    @Test(dataProvider = "additions")
    public void create(Entity entity) {
        assertTrue(DatabaseBuilder.create(entity));
    }

    @Test
    public void create100Operations() {
        int size = 20;
        DatabaseBuilder.build(size);
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
                {new AlternativeAdditionRelationship(
                        new NumberNode(1),
                        new NumberNode(2),
                        new NumberNode(3))}
        };
    }
}
