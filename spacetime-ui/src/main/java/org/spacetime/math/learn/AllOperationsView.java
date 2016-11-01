package org.spacetime.math.learn;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import org.spacetime.backend.db.math.operations_alternative.*;
import org.spacetime.backend.db.math.utils.DatabaseUtils;
import org.spacetime.backend.db.math.utils.UserTutor;

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
        UserTutor<AlternativeAdditionRelationship> tutor = new UserTutor<>(AlternativeAdditionRelationship.class, "zuacaldeira@gmail.com");
        tutor.iterator().forEachRemaining(
            p -> {
                UI.getCurrent().access(() ->
                    {
                        addComponent(getComponentFor((AlternativeOperationRelationship) p));
                    });
            }
        );
    }

    private Component getComponentFor(AlternativeOperationRelationship p) {
        return new MathOperationView(p);
    }


}
