package org.spacetime.backend.db.math.operations_old;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.spacetime.backend.db.math.NumberNode;

/**
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "x")
public class OldMultiplicationRelationshipOld extends OldOperationRelationship {
    public OldMultiplicationRelationshipOld(NumberNode a, NumberNode b, NumberNode c) {
        super(a, b, c);
    }
}
