package org.spacetime.math;

import org.spacetime.components.LifetimeServiceView;

/**
 * Created by zua on 21/10/16.
 */
public class PracticeService extends LifetimeServiceView {
    public PracticeService() {
    }

    public void addProblem(ProblemView v) {
        addComponent(v);
    }
}
