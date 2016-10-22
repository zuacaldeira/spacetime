package org.spacetime;

import com.vaadin.ui.Button;

/**
 * Created by zua on 21/10/16.
 */
public class SessionTypesSpacetimeView extends SpacetimeView {
    @Override
    protected SpacetimeMenu createMenu() {
        return new SpacetimeMenu();
    }

    @Override
    protected SpaceTimeBody createMain() {
        return new SpaceTimeBody();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

    }
}
