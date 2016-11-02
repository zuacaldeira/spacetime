package org.spacetime.backend.services;

import org.spacetime.backend.db.relationships.OperationRelationship;
import org.spacetime.backend.utils.DatabaseUtils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 27/10/16.
 */
public abstract class Tutor<T extends OperationRelationship> implements Iterable<T> {

    private final Class<T> operationClass;
    private List<T> problems;

    public Tutor(Class<T> aClass) {
        this.operationClass = aClass;
        this.problems = new LinkedList<>();
    }

    public List<T> getProblems() {
        return problems;
    }

    @Override
    public Iterator<T> iterator() {
        if(problems.isEmpty()) {
            loadProblems();
        }
        return problems.iterator();
    }

    private void loadProblems() {
        problems = DatabaseUtils.loadProblems(operationClass);
    }

    public boolean solve(OperationRelationship relationship, int c) {
        return relationship.getResult().getValue() == c;
    }

}
