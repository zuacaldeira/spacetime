package org.spacetime.math.learn;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import org.spacetime.backend.db.relationships.Addition;
import org.spacetime.backend.db.relationships.OperationRelationship;
import org.spacetime.backend.services.UserTutor;

/**
 * Created by zua on 30/10/16.
 */
public class AllOperationsView extends GridLayout {



    public AllOperationsView() {
        super();
        setWidth("100%");
        setColumns(16);
        init();
    }

    private void init() {
        UserTutor<Addition> tutor = new UserTutor<>(Addition.class, "zuacaldeira@gmail.com");
        tutor.iterator().forEachRemaining(
            p -> {
                UI.getCurrent().access(() ->
                    {
                        addComponent(getComponentFor((OperationRelationship) p));
                    });
            }
        );
    }

    private Component getComponentFor(OperationRelationship p) {
        return new MathOperationView(p);
    }


}
