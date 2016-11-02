package org.spacetime.backend.db.relationships;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.StartNode;
import org.spacetime.backend.db.entities.NumberNode;
import org.spacetime.backend.db.entities.PrimeNode;

import java.util.Objects;

/**
 * Created by zua on 01/11/16.
 */
@org.neo4j.ogm.annotation.RelationshipEntity(type = "IS_PRIME")
public class IsPrime extends RelationshipEntity {

    @StartNode
    private NumberNode start;

    @EndNode
    private PrimeNode end;

    public IsPrime() {
    }

    public IsPrime(NumberNode prime, PrimeNode primeNode) {
        this.start = prime;
        this.end = primeNode;
    }


    public NumberNode getStart() {
        return start;
    }

    public PrimeNode getEnd() {
        return end;
    }

    public void setStart(NumberNode start) {
        this.start = start;
    }

    public void setEnd(PrimeNode end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IsPrime isPrime = (IsPrime) o;
        return Objects.equals(start, isPrime.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start);
    }
}
