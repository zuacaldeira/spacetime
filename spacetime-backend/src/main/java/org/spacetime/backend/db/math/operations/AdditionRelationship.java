package org.spacetime.backend.db.math.operations;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.math.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "+")
public class AdditionRelationship extends OperationRelationship {
    public AdditionRelationship(NumberNode a, NumberNode b, NumberNode c) {
        super(a, b, c);
    }
}
