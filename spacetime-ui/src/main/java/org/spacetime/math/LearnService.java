package org.spacetime.math;

import org.spacetime.components.LifetimeServiceView;

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
