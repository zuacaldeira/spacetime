package org.spacetime.math;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import org.spacetime.components.SpaceTimeBody;
import org.spacetime.components.SpacetimeButton;
import org.spacetime.components.SpacetimeMenu;
import org.spacetime.components.SpacetimeView;
import org.spacetime.services.portals.ServicePortal;

/**
 * Created by zua on 21/10/16.
 */
public class MathSpacetimeView extends SpacetimeView implements View {

    public static final String NAME = "MATH";

    private ServicePortal numbers;
    private ServicePortal operations;
    private ServicePortal properties;
    private ServicePortal strategies;
    private ServicePortal applications;
    private NumbersChart numbersChart;
    private NumbersChart operationsChart;
    private NumbersChart propertiesChart;
    private NumbersChart strategiesChart;
    private NumbersChart applicationsChart;

    public MathSpacetimeView() {
    }

    protected SpaceTimeBody createMain() {
        SpaceTimeBody body = new SpaceTimeBody();
        // Numbers
        numbers = createPortal("Numbers", getDummyDescription1());
        numbersChart = new CentesimalChart();
        numbers.addComponent(numbersChart);

        operations = createPortal("Operations", getDummyDescription2());
        operationsChart = new CentesimalChart();
        operations.addComponent(operationsChart);

        properties = createPortal("Properties", getDummyDescription2());
        propertiesChart = new CentesimalChart();
        properties.addComponent(propertiesChart);

        strategies = createPortal("Strategies", getDummyDescription2());
        strategiesChart = new CentesimalChart();
        strategies.addComponent(strategiesChart);

        applications = createPortal("Applications", getDummyDescription1());
        applicationsChart = new CentesimalChart();
        applications.addComponent(applicationsChart);

        body.addServicePortal(numbers);
        body.addServicePortal(operations);
        body.addServicePortal(properties);
        body.addServicePortal(strategies);
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("Changing View inside Math");
    }
}
