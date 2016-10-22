package org.spacetime;

import com.vaadin.ui.Component;

/**
 * Created by zua on 21/10/16.
 */
public class Stars extends RelativeLayout {
    private final int nStars;

    public Stars(int nStars) {
        this.nStars = nStars;
        setSizeFull();
        initStars();
        setStyleName("stars");
    }

    private void initStars() {
        for(int i = 0; i < nStars; i++) {
            addComponent(getNewStar(), getRandomPosition().getCSSString());
        }
    }

    private Component getNewStar() {
        Component star = new Star();
        star.setStyleName("star");
        return star;
    }

    private ComponentPosition getRandomPosition() {
        ComponentPosition position = new ComponentPosition();
        position.setLeft(getRandom()*100, Unit.PERCENTAGE);
        position.setBottom(getRandom()*100, Unit.PERCENTAGE);
        return position;
    }

    private float getRandom() {
        return (float) Math.random();
    }

    public int getNStars() {
        return nStars;
    }
}
