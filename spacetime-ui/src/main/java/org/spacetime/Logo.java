package org.spacetime;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by zua on 19/10/16.
 */
public class Logo extends RelativeLayout {

    public Logo() {
        super(48,48);
        addComponents(getStars(), getSun());
        setStyleName("logo");
        setSizeUndefined();
    }

    private Component getStars() {
        Stars stars = new Stars(3);
        stars.setWidth(getWidth(), getWidthUnits());
        stars.setHeight(getHeight(), getHeightUnits());
        return stars;
    }

    private Component getSun() {
        VerticalLayout sun = new Sun();
        setWidth(getWidth(), getWidthUnits());
        setHeight(getHeight(), getHeightUnits());
        return sun;
    }
}
