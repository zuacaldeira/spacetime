package org.spacetime;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

/**
 * Created by zua on 21/10/16.
 */
public class MathSpacetimeView extends SpacetimeView {

    public MathSpacetimeView() {

    }

    protected SpaceTimeBody createMain() {
        SpaceTimeBody body = new SpaceTimeBody();
        // Numbers
        ServicePortal numbers = createPortal("Numbers", getDummyDescription1());
        numbers.addComponent(new NumbersChart(0,100));
        body.addServicePortal(numbers);

        ServicePortal operations = createPortal("Operations", getDummyDescription2());
        body.addServicePortal(operations);
        ServicePortal strategies = createPortal("Strategies", getDummyDescription2());
        body.addServicePortal(strategies);
        ServicePortal applications = createPortal("Applications", getDummyDescription1());
        body.addServicePortal(applications);
        return body;
    }

    private ServicePortal createPortal(String name, String summary) {
        ServicePortal portal = new ServicePortal(name, summary);
        return portal;
    }

    protected SpacetimeMenu createMenu() {
        SpacetimeMenu menu = new SpacetimeMenu();
        menu.addMenuItem(new SpacetimeButton("Play", FontAwesome.GAMEPAD));
        menu.addMenuItem(new SpacetimeButton("Learn", FontAwesome.BOOK));
        menu.addMenuItem(new SpacetimeButton("Practice", FontAwesome.PENCIL));
        return menu;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

    }
}
