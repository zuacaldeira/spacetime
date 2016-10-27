package org.spacetime;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import org.spacetime.actors.Master;

import java.util.Random;

/**
 * Created by zua on 22/10/16.
 */
public class NumbersChart extends HorizontalLayout {

    private final int min;
    private final int max;
    private int count;

    public NumbersChart(int min, int max) {
        this.min = min;
        this.max = max;
        this.count = max - min + 1;
        setSizeFull();
        setStyleName("chart");
        setMargin(true);
        setCaption(getCaption(count));
        initBars();
    }

    private String getCaption(int i) {
        return String.valueOf(i) + "/" + (max-min+1);
    }

    protected void initBars() {
        System.out.println("Starting Bars... on new Thread");
        ActorRef actor = ActorSystem.create().actorOf(Props.create(Master.class), "Chart" + new Random().nextFloat());
        actor.tell(this, ActorRef.noSender());
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public void onActorUpdate(int start, int end, float... heights) {
        new Thread(() -> {
            UI ui = UI.getCurrent();
            if(ui != null) {
                ui.access(() -> {
                    System.out.println("Inside UI...");
                    for(int i = 0; i < heights.length; i++) {
                        addBar(start+i, heights[i]);
                        if(i == heights.length/2) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }).start();
    }

    private void addBar(int col, float height) {
        VerticalLayout bar = new VerticalLayout();
        bar.setHeight(height, Unit.PIXELS);
        bar.setStyleName("bar");
        addComponent(bar);
        setComponentAlignment(bar, Alignment.BOTTOM_CENTER);
        count--;
        setCaption(getCaption(count));
    }
}
