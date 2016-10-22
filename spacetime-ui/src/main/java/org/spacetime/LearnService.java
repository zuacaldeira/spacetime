package org.spacetime;

/**
 * Created by zua on 21/10/16.
 */
public class LearnService extends LifetimeServiceView {

    public LearnService() {
    }

    public void addStrategy(StrategyView v) {
        addComponent(v);
    }
}
