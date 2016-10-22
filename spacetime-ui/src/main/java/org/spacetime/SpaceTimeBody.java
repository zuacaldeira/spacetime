package org.spacetime;

import com.vaadin.ui.VerticalLayout;

/**
 * Created by zua on 21/10/16.
 */
public class SpaceTimeBody extends VerticalLayout {

    public SpaceTimeBody() {
        setWidth("60%");
        setHeight("80%");
        setSpacing(true);
        setMargin(true);
    }

    public void addServicePortal(ServicePortal addition) {
        VerticalLayout base = new VerticalLayout(addition);
        base.setSizeFull();
        base.setStyleName("portal-background");
        addComponent(base);
    }
}
