package org.spacetime.backend.db.math;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.Entity;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "/")
public class DivisionRelationship extends OperationRelationship {
    public DivisionRelationship(NumberNode c, NumberNode b, NumberNode a) {
        super(c, b, a);
    }
}
