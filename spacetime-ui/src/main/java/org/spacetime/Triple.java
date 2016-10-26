package org.spacetime;

/**
 * Created by zua on 22/10/16.
 */
public class Triple<T, T1, T2> extends Pair<T,T1> {
    private final T2 third;

    public Triple(T first, T1 second, T2 third) {
        super(first, second);
        this.third = third;
    }

    public T2 getThird() {
        return third;
    }
}
