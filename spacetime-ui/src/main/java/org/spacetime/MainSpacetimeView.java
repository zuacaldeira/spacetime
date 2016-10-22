package org.spacetime;


import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

/**
 * Created by zua on 19/10/16.
 */
public class MainSpacetimeView extends SpacetimeView {
    private SpacetimeButton sessionTypesButton;
    private SpacetimeButton mathsButton;
    private SpacetimeButton blogButton;

    public MainSpacetimeView() {
    }

    @Override
    protected SpacetimeMenu createMenu() {
        SpacetimeMenu menu = new SpacetimeMenu();
        sessionTypesButton = new SpacetimeButton("Session Types");
        mathsButton = new SpacetimeButton("Maths");
        blogButton = new SpacetimeButton("Blog");

        sessionTypesButton.addClickListener(this);
        mathsButton.addClickListener(this);
        blogButton.addClickListener(this);

        menu.addMenuItem(sessionTypesButton).addMenuItem(mathsButton).addMenuItem(blogButton);
        return menu;
    }

    @Override
    protected SpaceTimeBody createMain() {
        SpaceTimeBody main = new SpaceTimeBody();
        ServicePortal mathemathicsPortal = new ServicePortal("Mathematics", getDummyDescription1());
        ServicePortal sessionTypesPortal = new ServicePortal("Session Types: Specification Driven Development", getDummyDescription2());
        ServicePortal blogPortal = new ServicePortal("Blog", getDummyDescription1());
        main.addServicePortal(mathemathicsPortal);
        main.addServicePortal(sessionTypesPortal);
        main.addServicePortal(blogPortal);
        return main;
    }

    private Component createBlogSpacetimeView() {
        return new BlogSpaceTimeView();
    }

    private Component createSessionTypesSpacetimeView() {
        return new SessionTypesSpacetimeView();
    }

    private Component createMathSpacetimeView() {
        return new MathSpacetimeView();
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        Notification.show("Button clicked");
        if(clickEvent.getButton() == sessionTypesButton) {
            getUI().setContent(createSessionTypesSpacetimeView());
        }
        else if(clickEvent.getButton() == mathsButton) {
            getUI().setContent(createMathSpacetimeView());
        }
        else if(clickEvent.getButton() == blogButton) {
            getUI().setContent(createBlogSpacetimeView());
        }
    }
}