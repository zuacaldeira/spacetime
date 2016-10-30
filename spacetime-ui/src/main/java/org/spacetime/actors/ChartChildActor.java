package org.spacetime.actors;

import akka.actor.UntypedActor;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.VerticalLayout;
import org.spacetime.math.BarsMessage;
import org.spacetime.math.ChartMessage;

/**
 * Created by zua on 29/10/16.
 */
public class ChartChildActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof ChartMessage) {
            System.out.println("Chart Child Actor received message with workload: " + ((ChartMessage) message).getWorkload());
            drawBar((ChartMessage) message);
        }
    }

    private void drawBar(ChartMessage message) {
        VerticalLayout[] layouts = new VerticalLayout[message.getWorkload()];
        for(int i = 0; i < message.getWorkload(); i++) {
            layouts[i] = getBar((float) Math.random()*100);
        }
        getSender().tell(new BarsMessage(layouts), getSelf());
    }


    private VerticalLayout getBar(float height) {
        VerticalLayout bar = new VerticalLayout();
        bar.setHeight(height, Sizeable.Unit.PIXELS);
        bar.setStyleName("bar");
        return bar;
    }



}
