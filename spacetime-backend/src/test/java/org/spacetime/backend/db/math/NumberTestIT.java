package org.spacetime.backend.db.math;

import org.spacetime.backend.db.AbstractEntityTestIT;
import org.spacetime.backend.db.Entity;
import org.spacetime.backend.db.Neo4JQueryFactory;
import org.spacetime.backend.db.NodeEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;

/**
 * Created by zua on 26/10/16.
 */
public class NumberTestIT extends AbstractEntityTestIT {

    @Test
    public void constructEntityTest() {
        NumberNode number = new NumberNode();
        assertNull(number.getValue());

        NumberNode number2 = new NumberNode(0);
        assertTrue(number2.getValue() == 0);
    }


    @Override
    @Test(dataProvider = "numbers")
    public void create(Entity node) {
        if(node instanceof  NumberNode) {
            NumberNode number = (NumberNode) node;
            getNeo4JSession().save(number);

            NumberNode result = getNeo4JSession().queryForObject(
                    NumberNode.class,
                    Neo4JQueryFactory.getNumberNodeWithValue(number.getValue()),
                    new HashMap());

            assertNotNull(result);
            System.out.println(result.getValue());
        }
    }

    @Test
    public void create100NodesTest() {
        for(int i = 0; i <= 100; i++) {
            create(new NumberNode(i));
        }
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




    @DataProvider(name = "numbers")
    public Object[][] numbers() {
        return new Object[][] {
                {new NumberNode(100)}
        };
    }

}