package org.spacetime;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.spacetime.backend.db.relationships.OperationRelationship;
import org.spacetime.backend.services.UserTutor;

import javax.servlet.annotation.WebServlet;

/**
 *
 */
@Theme("mytheme")
@Push
public class SpaceTimeUI extends UI {

    private UserTutor<?> tutor;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        MainSpacetimeView layout = new MainSpacetimeView();
        setContent(layout);
        String username = "guest";
        tutor = new UserTutor<OperationRelationship>(OperationRelationship.class, username);
    }

    public UserTutor<?> getTutor() {
        return tutor;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SpaceTimeUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
