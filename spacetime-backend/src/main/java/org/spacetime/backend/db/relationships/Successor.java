package org.spacetime.backend.db.relationships;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.StartNode;
import org.spacetime.backend.db.entities.NumberNode;

import java.util.Objects;

/**
 * Created by zua on 27/10/16.
 */
@org.neo4j.ogm.annotation.RelationshipEntity(type = "SUC_BY")
public class Successor extends RelationshipEntity {

    @StartNode
    private NumberNode predecessor;

    @EndNode
    private NumberNode successor;

    public Successor() {
    }

    public Successor(NumberNode successor, NumberNode predecessor) {
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public NumberNode getPredecessor() {
        return predecessor;
    }

    public NumberNode getSuccessor() {
        return successor;
    }

    public void setPredecessor(NumberNode predecessor) {
        this.predecessor = predecessor;
    }

    public void setSuccessor(NumberNode successor) {
        this.successor = successor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Successor that = (Successor) o;
        return Objects.equals(predecessor, that.predecessor) &&
                Objects.equals(successor, that.successor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predecessor, successor);
    }
}
