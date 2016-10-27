package org.spacetime.backend.db.math.utils;

import org.spacetime.backend.db.math.operations.OperationRelationship;
import org.spacetime.backend.db.math.operations_alternative.*;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

/**
 * Created by zua on 27/10/16.
 */
public class UserTutorTest {

    private static final String USERNAME = "zuacaldeira@gmail.com";
    private int numberOfProblems = 0;

    @Test
    public void createTutorTest() {
        createAdditionTutor();
        createSubtractionTutor();
        createMultiplicationTutor();
        createDivisionTutor();
        System.out.println("#Problems = " + numberOfProblems);
    }

    private void createDivisionTutor() {
        UserTutor divisionTutor = new UserTutor<AlternativeDivisionRelationship>(AlternativeDivisionRelationship.class, USERNAME);
        Iterator<AlternativeDivisionRelationship> it = divisionTutor.iterator();
        numberOfProblems += divisionTutor.getProblems().size();
        System.out.println("Tutor Initiated with " + divisionTutor.getProblems().size() + " problems.");
        while(it.hasNext()) {
            System.out.printf(it.next() + "\n");
        }
    }

    private void createMultiplicationTutor() {
        UserTutor multiplicationTutor = new UserTutor<AlternativeMultiplicationRelationship>(AlternativeMultiplicationRelationship.class, USERNAME);
        Iterator<AlternativeMultiplicationRelationship> it = multiplicationTutor.iterator();
        numberOfProblems += multiplicationTutor.getProblems().size();
        System.out.println("Tutor Initiated with " + multiplicationTutor.getProblems().size() + " problems.");
        while(it.hasNext()) {
            System.out.printf(it.next() + "\n");
        }
    }

    private void createSubtractionTutor() {
        UserTutor subtractionTutor = new UserTutor<AlternativeSubtractionRelationship>(AlternativeSubtractionRelationship.class, USERNAME);
        Iterator<AlternativeSubtractionRelationship> it = subtractionTutor.iterator();
        numberOfProblems += subtractionTutor.getProblems().size();
        System.out.println("Tutor Initiated with " + subtractionTutor.getProblems().size() + " problems.");
        while(it.hasNext()) {
            System.out.printf(it.next() + "\n");
        }
    }

    private void createAdditionTutor() {
        UserTutor addditionTutor = new UserTutor<AlternativeAdditionRelationship>(AlternativeAdditionRelationship.class, USERNAME);
        Iterator<AlternativeAdditionRelationship> it = addditionTutor.iterator();
        numberOfProblems += addditionTutor.getProblems().size();
        System.out.println("Tutor Initiated with " + addditionTutor.getProblems().size() + " problems.");
        while(it.hasNext()) {
            System.out.printf(it.next() + "\n");
        }
    }
}