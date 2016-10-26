package org.spacetime.backend.db.math;

import org.neo4j.ogm.typeconversion.AttributeConverter;

/**
 * Created by zua on 26/10/16.
 */
public class NumberNodeConverter implements AttributeConverter<NumberNode, Integer> {
    @Override
    public Integer toGraphProperty(NumberNode numberNode) {
        return numberNode.getValue();
    }

    @Override
    public NumberNode toEntityAttribute(Integer integer) {
        return new NumberNode(integer);
    }
}
