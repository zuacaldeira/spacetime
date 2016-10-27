package org.spacetime.backend.db.math.operations_alternative;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.math.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "x")
public class AlternativeMultiplicationRelationship extends AlternativeOperationRelationship {
    public AlternativeMultiplicationRelationship(NumberNode a, NumberNode b, NumberNode c) {
        super(a, b, c);
    }
    public AlternativeMultiplicationRelationship() {}
}
