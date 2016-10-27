package org.spacetime.backend.db.math.operations_alternative;

import org.spacetime.backend.db.math.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@org.neo4j.ogm.annotation.RelationshipEntity(type = "-")
public class AlternativeSubtractionRelationship extends AlternativeOperationRelationship {

    public AlternativeSubtractionRelationship(NumberNode c, NumberNode b, NumberNode a) {
        super(c, b, a);
    }
}
