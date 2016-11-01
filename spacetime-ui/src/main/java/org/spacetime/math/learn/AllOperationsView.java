package org.spacetime.math.learn;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import org.spacetime.backend.db.math.operations_alternative.*;
import org.spacetime.backend.db.math.utils.DatabaseUtils;

/**
 * Created by zua on 30/10/16.
 */
public class OperationView extends GridLayout {



    public OperationView() {
        super();
        init();
    }

    private void init() {
        DatabaseUtils.loadAllProblems().forEach(p -> {addComponent(getComponentFor((AlternativeOperationRelationship) p));});
    }

    private Component getComponentFor(AlternativeOperationRelationship p) {
        return new MathOperationView(p);
    }


}
