package org.spacetime.backend.db.math.operations_alternative;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.math.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "+")
public class AlternativeAdditionRelationship extends AlternativeOperationRelationship {

    public AlternativeAdditionRelationship(NumberNode a, NumberNode b, NumberNode c) {
        super(a, b, c);
    }
    public AlternativeAdditionRelationship() {
        super();
    }

    @Override
    public String toString() {
        return getOperands().getLeft().toString() +
                " + " + getOperands().getRight().toString() +
                " = " + getResult().toString();
    }

}
