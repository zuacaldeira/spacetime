package org.spacetime.backend.db.math.operations_alternative;

import org.spacetime.backend.db.NodeEntity;
import org.spacetime.backend.db.math.NumberNode;

import java.util.Objects;

/**
 * Created by zua on 27/10/16.
 */
public class Operands extends NodeEntity {
    private NumberNode left;
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
}
