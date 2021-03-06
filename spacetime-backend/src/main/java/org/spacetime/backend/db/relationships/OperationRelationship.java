package org.spacetime.backend.db.relationships;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.spacetime.backend.db.entities.NumberNode;

import java.util.Objects;

/**
 * This class represents addition as a relationship between number nodes.
 *
 * The mathematical expression a op b = c is represented can be created by instantiating
 * {left := a; right:= b; result:= c}
 *
 * Created by zua on 26/10/16.
 */
@RelationshipEntity(type = "°")
public abstract class OperationRelationship extends org.spacetime.backend.db.relationships.RelationshipEntity {

    /**
     * The operations's operands
     */
    @StartNode
    private Operands operands;


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
        this.operands = new Operands(left, right);
        this.result = result;
    }

    public OperationRelationship() {
    }


    /**
     * Returns the operands.
     *
     * @return The operands
     */
    public Operands getOperands() {
        return operands;
    }


    /**
     * Return the result.
     *
     * @return The underlying operation result
     */
    public NumberNode getResult() {
        return result;
    }


    public void setOperands(Operands operands) {
        this.operands = operands;
    }

    public void setResult(NumberNode result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OperationRelationship that = (OperationRelationship) o;
        return Objects.equals(operands, that.operands) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operands, result);
    }


    public abstract String getOperationSign();

}
