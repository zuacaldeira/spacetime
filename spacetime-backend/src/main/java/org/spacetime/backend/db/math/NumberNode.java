package org.spacetime.backend.db.math;

import org.spacetime.backend.db.NodeEntity;

import java.util.Objects;

/**
 * Created by zua on 26/10/16.
 */
public class NumberNode extends NodeEntity {

    private Integer value;

    public NumberNode() {
    }

    public NumberNode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NumberNode that = (NumberNode) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
