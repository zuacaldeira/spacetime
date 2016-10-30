package org.spacetime.math;

import com.vaadin.ui.Component;

import java.util.List;

/**
 * Created by zua on 29/10/16.
 */
public class BarsMessage {
    private Component[] components;

    public BarsMessage(Component[] components) {
        this.components = components;
    }

    public Component[] getComponents() {
        return components;
    }
}
