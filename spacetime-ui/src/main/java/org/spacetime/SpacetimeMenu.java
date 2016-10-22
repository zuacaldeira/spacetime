package org.spacetime;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

/**
 * Created by zua on 21/10/16.
 */
public class SpacetimeMenu extends HorizontalLayout {

    private HorizontalLayout menuItems;

    public SpacetimeMenu() {
        setSizeFull();
        setHeightUndefined();
        setMargin(true);
        setSpacing(true);
        menuItems = new HorizontalLayout();
        menuItems.setSizeUndefined();
        menuItems.setSpacing(true);
        addComponents(menuItems);
        setComponentAlignment(menuItems, Alignment.MIDDLE_RIGHT);
        setStyleName("menu");
    }

    public SpacetimeMenu addMenuItem(SpacetimeButton menuItem) {
        menuItems.addComponent(menuItem);
        return this;
    }
}
