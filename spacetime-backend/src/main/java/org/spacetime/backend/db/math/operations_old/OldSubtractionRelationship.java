package org.spacetime.backend.db.math.operations_old;

import org.spacetime.backend.db.math.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@org.neo4j.ogm.annotation.RelationshipEntity(type = "-")
public class OldSubtractionRelationship extends OldOperationRelationship {

    public OldSubtractionRelationship(NumberNode c, NumberNode b, NumberNode a) {
        super(c, b, a);
    }
}
