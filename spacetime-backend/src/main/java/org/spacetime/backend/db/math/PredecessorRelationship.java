package org.spacetime.backend.db.math;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.StartNode;
import org.spacetime.backend.db.Entity;
import org.spacetime.backend.db.RelationshipEntity;

import java.util.Objects;

/**
 * Created by zua on 27/10/16.
 */
@org.neo4j.ogm.annotation.RelationshipEntity(type = "before")
public class PredecessorRelationship extends RelationshipEntity {

    @StartNode
    private NumberNode predecessor;

    @EndNode
    private NumberNode successor;

    public PredecessorRelationship() {
    }

    public PredecessorRelationship(NumberNode predecessor, NumberNode successor) {
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
        PredecessorRelationship that = (PredecessorRelationship) o;
        return Objects.equals(predecessor, that.predecessor) &&
                Objects.equals(successor, that.successor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predecessor, successor);
    }
}
