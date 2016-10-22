package org.spacetime;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.Random;

/**
 * Created by zua on 22/10/16.
 */
public class NumbersChart extends HorizontalLayout {

    private final int min;
    private final int max;

    public NumbersChart(int min, int max) {
        this.min = min;
        this.max = max;
        initBars();
        setSizeFull();
        setStyleName("chart");
    }

    private void initBars() {
        int rate = (max-min)/10;
        for(int i = min; i < max; i++) {
            VerticalLayout bar = new VerticalLayout();
            bar.setStyleName("bar");
            bar.setHeight(new Random().nextFloat()*100, Unit.PIXELS);


            String lCaption = (i%rate == 0) ? String.valueOf(i) : "";
            Label label = new Label(lCaption);
            label.addStyleName("number");
            label.setSizeUndefined();

            //bar.addComponent(label);
            //bar.setComponentAlignment(label, Alignment.BOTTOM_CENTER);
            //addComponent(bar);
            VerticalLayout labeledBar = new VerticalLayout(bar, label);
            labeledBar.setExpandRatio(bar, .9f);
            labeledBar.setExpandRatio(label, .1f);
            labeledBar.setComponentAlignment(bar, Alignment.BOTTOM_CENTER);
            labeledBar.setComponentAlignment(label, Alignment.BOTTOM_CENTER);

            addComponent(labeledBar);
            setComponentAlignment(labeledBar, Alignment.BOTTOM_CENTER);

        }
    }
}
