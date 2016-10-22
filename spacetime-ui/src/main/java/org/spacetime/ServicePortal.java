package org.spacetime;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by zua on 20/10/16.
 */
public class ServicePortal extends VerticalLayout {
    private Label title;
    private Label summary;

    public ServicePortal(String name) {
        this(name, "");
    }

    public ServicePortal(String name, String summary) {
        initTitle(name);
        initSummary(summary);

        addComponents(title, this.summary);

        setSizeFull();
        setHeightUndefined();
        setMargin(true);
        setSpacing(true);
        setStyleName("portal");
    }

    private void initSummary(String text) {
        summary = new Label(text);
        summary.setSizeFull();
        summary.setHeightUndefined();
    }

    private void initTitle(String name) {
        title = new Label(name);
        title.setSizeFull();
        title.setHeightUndefined();
        title.setStyleName("portal-name");
    }

    public Label getTitle() {
        return title;
    }

    public Label getSummary() {
        return summary;
    }
}
