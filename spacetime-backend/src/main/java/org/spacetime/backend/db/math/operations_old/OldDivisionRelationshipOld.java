package org.spacetime.backend.db.math.operations_old;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.math.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "/")
public class OldDivisionRelationshipOld extends OldOperationRelationship {
    public OldDivisionRelationshipOld(NumberNode c, NumberNode b, NumberNode a) {
        super(c, b, a);
    }
}
