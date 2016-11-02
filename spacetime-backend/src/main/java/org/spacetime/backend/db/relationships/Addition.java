package org.spacetime.backend.db.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.entities.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "+")
public class Addition extends OperationRelationship {

    public Addition(NumberNode a, NumberNode b, NumberNode c) {
        super(a, b, c);
    }
    public Addition() {
        super();
    }

    @Override
    public String getOperationSign() {
        return "+";
    }

    @Override
    public String toString() {
        return getOperands().getLeft().toString() +
                " + " + getOperands().getRight().toString() +
                " = " + getResult().toString();
    }

}
