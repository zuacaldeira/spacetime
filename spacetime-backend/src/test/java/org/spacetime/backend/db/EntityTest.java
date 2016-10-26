package org.spacetime.backend.db;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 26/10/16.
 */
public class EntityTest {


    @Test
    public void testCreateEntity() {
        Entity e = new Entity(){};
        assertNull(e.getId());
        Entity e2 = new Entity(10L){};
        assertTrue(e2.getId() == 10L);
    }



}