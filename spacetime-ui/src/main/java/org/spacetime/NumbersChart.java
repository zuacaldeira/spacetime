package org.spacetime;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.Random;

/**
 * Created by zua on 22/10/16.
 */
public class NumbersChart extends HorizontalLayout {

    private final int min;
    private final int max;
    private int count = 0;

    public NumbersChart(int min, int max) {
        this.min = min;
        this.max = max;
        setSizeFull();
        setStyleName("chart");
        setMargin(true);
        setCaption(getCaption(0));
        initBars();
    }

    private String getCaption(int i) {
        return String.valueOf(i) + "/" + (max-min+1);
    }

    private void initBars() {
        System.out.println("Starting Bars...");
        ActorRef actor = ActorSystem.create().actorOf(Props.create(Master.class), "Chart" + new Random().nextFloat());
        actor.tell(this, ActorRef.noSender());
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public void onActorUpdate(int i, float height) {
        //System.out.println("Actor tries to update " + i + " with height " + height);
        if(getUI() != null) {
            getUI().access(() -> {
                VerticalLayout bar = new VerticalLayout();
                bar.setStyleName("bar");
                bar.setHeight(height, Unit.PIXELS);
                bar.addStyleName("random-color");
                addComponent(bar);
                setComponentAlignment(bar, Alignment.BOTTOM_CENTER);
                count++;
                setCaption(getCaption(count));
            });
        }
    }
}
