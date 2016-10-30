package org.spacetime;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import org.spacetime.blogs.BlogSpaceTimeView;
import org.spacetime.components.SpaceTimeBody;
import org.spacetime.components.SpacetimeButton;
import org.spacetime.components.SpacetimeMenu;
import org.spacetime.components.SpacetimeView;
import org.spacetime.math.MathSpacetimeView;
import org.spacetime.services.portals.ServicePortal;
import org.spacetime.session.SessionTypesSpacetimeView;

/**
 * Created by zua on 19/10/16.
 */
public class MainSpacetimeView extends SpacetimeView implements View {
    public static final String NAME = "";
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
        MathSpacetimeView mathView = new MathSpacetimeView();
        return mathView;
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("Changing View inside Main");
    }
}
