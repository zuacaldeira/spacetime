package org.spacetime.math;

import org.spacetime.components.LifetimeServiceView;

/**
 * Created by zua on 21/10/16.
 */
public class PlayService extends LifetimeServiceView {

    public PlayService() {
    }


    public void addGame(GameView g) {
        addComponent(g);
    }
}
