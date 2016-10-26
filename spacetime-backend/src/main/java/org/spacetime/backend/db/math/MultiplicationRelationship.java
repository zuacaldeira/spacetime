package org.spacetime.backend.db.math;

import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "x")
public class MultiplicationRelationship extends OperationRelationship {
    public MultiplicationRelationship(NumberNode a, NumberNode b, NumberNode c) {
        super(a, b, c);
    }
}
