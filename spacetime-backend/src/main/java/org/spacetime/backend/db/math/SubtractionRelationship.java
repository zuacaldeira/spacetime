package org.spacetime.backend.db.math;

import org.spacetime.backend.db.Entity;
import org.spacetime.backend.db.RelationshipEntity;

/**
 * Created by zua on 26/10/16.
 */
@org.neo4j.ogm.annotation.RelationshipEntity(type = "-")
public class SubtractionRelationship extends OperationRelationship {

    public SubtractionRelationship(NumberNode c, NumberNode b, NumberNode a) {
        super(c, b, a);
    }
}
