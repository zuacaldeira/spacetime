package org.spacetime.blogs;

import com.vaadin.ui.Button;
import org.spacetime.components.SpaceTimeBody;
import org.spacetime.components.SpacetimeMenu;
import org.spacetime.components.SpacetimeView;

/**
 * Created by zua on 21/10/16.
 */
public class BlogSpaceTimeView extends SpacetimeView {
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
