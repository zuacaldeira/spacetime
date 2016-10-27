package org.spacetime.backend.db.math.utils;

import org.spacetime.backend.db.math.operations.OperationRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeOperationRelationship;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

/**
 * Created by zua on 27/10/16.
 */
public class UserTutorTest {

    private static final String USERNAME = "zuacaldeira@gmail.com";

    @Test
    public void createTutorTest() {
        UserTutor tutor = new UserTutor(USERNAME);
        Iterator<?> it = tutor.iterator();
        System.out.println("Tutor Initiated with " + tutor.getProblems().size() + " problems.");
    }
}