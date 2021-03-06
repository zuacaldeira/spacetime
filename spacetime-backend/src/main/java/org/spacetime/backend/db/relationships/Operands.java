package org.spacetime.backend.db.relationships;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.spacetime.backend.db.entities.NodeEntity;
import org.spacetime.backend.db.entities.NumberNode;
import org.spacetime.backend.db.entities.NumberNodeConverter;

import java.util.Objects;

/**
 * Created by zua on 27/10/16.
 */
public class Operands extends NodeEntity {

    @Property
    @Convert(NumberNodeConverter.class)
    private NumberNode left;

    @Property
    @Convert(NumberNodeConverter.class)
    private NumberNode right;

    public Operands() {
    }

    public Operands(NumberNode left, NumberNode right) {
        this.left = left;
        this.right = right;
    }


    /**
     * Retrieves the left operand.
     *
     * @return The left operand
     */
    public NumberNode getLeft() {
        return left;
    }

    /**
     * Retrieves the right operand.
     *
     * @return The right operand
     */
    public NumberNode getRight() {
        return right;
    }

    public void setLeft(NumberNode left) {
        this.left = left;
    }

    public void setRight(NumberNode right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operands operands = (Operands) o;
        return Objects.equals(left, operands.left) &&
                Objects.equals(right, operands.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    public String toString() {
        return "(" + left.toString() + ", " + right.toString() + ")";
    }
}
