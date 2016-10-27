package org.spacetime.backend.db.math.operations;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.math.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "/")
public class DivisionRelationship extends OperationRelationship {
    public DivisionRelationship(NumberNode c, NumberNode b, NumberNode a) {
        super(c, b, a);
    }
}
