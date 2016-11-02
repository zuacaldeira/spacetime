package org.spacetime.backend.db.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.entities.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "/")
public class Division extends OperationRelationship {
    public Division(NumberNode c, NumberNode b, NumberNode a) {
        super(c, b, a);
    }
    public Division() {}

    @Override
    public String getOperationSign() {
        return "/";
    }

    @Override
    public String toString() {
        return getOperands().getLeft().toString() +
                " / " + getOperands().getRight().toString() +
                " = " + getResult().toString();
    }
}
