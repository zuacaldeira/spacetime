package org.spacetime;

import com.vaadin.ui.HorizontalLayout;

/**
 * Created by zua on 21/10/16.
 */
public class PortalServices extends HorizontalLayout {

    public PortalServices() {
        setSizeFull();
    }

    public boolean hasServices() {
        return getComponentCount() > 0;
    }
}
