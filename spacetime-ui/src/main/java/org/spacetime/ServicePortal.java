package org.spacetime;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by zua on 20/10/16.
 */
public class ServicePortal extends VerticalLayout {
    private final Label title;
    private final Label summary;
    private HorizontalLayout services;


    public ServicePortal(String name) {
        this(name, "");
    }

    public ServicePortal(String name, String summary) {
        this.title = new Label(name);
        title.setSizeFull();
        title.setStyleName("portal-name");

        this.summary = new Label(summary);
        this.summary.setSizeFull();

        services = new HorizontalLayout();
        services.setSizeFull();
        services.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        setMargin(true);
        setSpacing(true);
        addComponents(title, this.summary, services);
        setStyleName("portal");
        setSizeFull();
        setHeightUndefined();
    }

    public Label getTitle() {
        return title;
    }

    public Label getSummary() {
        return summary;
    }


    public void addService(ServicePortal component) {
        services.addComponent(component);
    }
}
