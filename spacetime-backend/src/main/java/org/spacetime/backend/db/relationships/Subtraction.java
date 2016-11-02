package org.spacetime.backend.db.relationships;

import org.spacetime.backend.db.entities.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@org.neo4j.ogm.annotation.RelationshipEntity(type = "-")
public class Subtraction extends OperationRelationship {

    public Subtraction(NumberNode c, NumberNode b, NumberNode a) {
        super(c, b, a);
    }
    public Subtraction(){}

    @Override
    public String getOperationSign() {
        return "-";
    }

    @Override
    public String toString() {
        return getOperands().getLeft().toString() +
                " - " + getOperands().getRight().toString() +
                " = " + getResult().toString();
    }

}
