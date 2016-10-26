package org.spacetime.backend.db.math;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.Convert;

/**
 * This class represents addition as a relationship between number nodes.
 *
 * The mathematical expression a op b = c is represented can be created by instantiating
 * {left := a; right:= b; result:= c}
 *
 * Created by zua on 26/10/16.
 */
public class OperationRelationship extends org.spacetime.backend.db.RelationshipEntity {

    /**
     * The left operand
     */
    @StartNode
    private NumberNode left;


    /**
     * The right operand
     */
    @Property(name = "right")
    @Convert(NumberNodeConverter.class)
    private NumberNode right;

    /**
     * The result of this operation
     */
    @EndNode
    private NumberNode result;


    /**
     *
     */

    /**
     * Instantiates a new Addition relationship.
     *
     * @param left The left operand
     * @param right The right operand
     * @param result The result
     */
    public OperationRelationship(NumberNode left, NumberNode right, NumberNode result) {
        this.left = left;
        this.right = right;
        this.result = result;
    }


    /**
     * Returns the left operand.
     *
     * @return The left operand
     */
    public NumberNode getLeft() {
        return left;
    }


    /**
     * Returns the left operand.
     *
     * @return The left operand
     */
    public NumberNode getRight() {
        return right;
    }

    /**
     * Return the result.
     *
     * @return The underlying operation result
     */
    public NumberNode getResult() {
        return result;
    }
}
