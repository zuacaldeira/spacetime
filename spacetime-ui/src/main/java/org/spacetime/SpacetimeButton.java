package org.spacetime;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zua on 21/10/16.
 */
public class SpacetimeButton extends Button {
    public SpacetimeButton(String caption) {
        super(caption);
        setStyleName(ValoTheme.BUTTON_PRIMARY);
        addStyleName(ValoTheme.BUTTON_BORDERLESS);
        addStyleName("spacetime-button");
    }

    public SpacetimeButton(String caption, Resource icon) {
        this(caption);
        setIcon(icon);
    }
}
