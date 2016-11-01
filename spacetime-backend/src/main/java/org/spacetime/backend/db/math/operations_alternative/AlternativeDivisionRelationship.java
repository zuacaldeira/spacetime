package org.spacetime.backend.db.math.operations_alternative;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.math.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "/")
public class AlternativeDivisionRelationship extends AlternativeOperationRelationship {
    public AlternativeDivisionRelationship(NumberNode c, NumberNode b, NumberNode a) {
        super(c, b, a);
    }
    public AlternativeDivisionRelationship() {}

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
