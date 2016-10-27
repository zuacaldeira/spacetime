package org.spacetime.backend.db.math.utils;

import org.spacetime.backend.db.math.operations.OperationRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeOperationRelationship;
import org.spacetime.backend.db.math.operations_alternative.Operands;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by zua on 27/10/16.
 */
public abstract class Tutor<T extends AlternativeOperationRelationship> implements Iterable<T> {

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

    public boolean solve(AlternativeOperationRelationship relationship, int c) {
        return relationship.getResult().getValue() == c;
    }

}
