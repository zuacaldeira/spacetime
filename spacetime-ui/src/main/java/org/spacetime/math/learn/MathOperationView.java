package org.spacetime.math.learn;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.spacetime.backend.db.math.operations_alternative.AlternativeOperationRelationship;

/**
 * Created by zua on 30/10/16.
 */
public class MathOperationView extends HorizontalLayout {
    private final Label leftOperand;
    private final Label operationSign;
    private final Label rightOperand;
    private final Label equalsSign;
    private final Label result;

    public MathOperationView(AlternativeOperationRelationship p) {
        leftOperand = new Label(p.getOperands().getLeft().toString());
        operationSign = new Label(p.getOperationSign());
        rightOperand = new Label(p.getOperands().getRight().toString());
        equalsSign = new Label("=");
        result = new Label(p.getResult().toString());
        addComponents(leftOperand, operationSign, rightOperand, equalsSign, result);
    }
}
