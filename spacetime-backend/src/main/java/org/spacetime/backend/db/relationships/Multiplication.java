package org.spacetime.backend.db.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.entities.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "x")
public class Multiplication extends OperationRelationship {
    public Multiplication(NumberNode a, NumberNode b, NumberNode c) {
        super(a, b, c);
    }
    public Multiplication() {}

    @Override
    public String getOperationSign() {
        return "x";
    }

    @Override
    public String toString() {
        return getOperands().getLeft().toString() +
                " * " + getOperands().getRight().toString() +
                " = " + getResult().toString();
    }
}
