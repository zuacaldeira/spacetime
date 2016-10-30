package org.spacetime.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.VerticalLayout;
import org.spacetime.math.ChartMessage;
import org.spacetime.math.NumbersChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zua on 22/10/16.
 */
public class ChartActor extends UntypedActor {

    private int WORK = 0;
    private int REST = 0;
    private int FREE = 0;
    private Router router = null;

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof NumbersChart) {
            drawBars((NumbersChart) message);
        }
        else if (message instanceof Terminated) {
            router = router.removeRoutee(((Terminated) message).actor());
            ActorRef r = getContext().actorOf(Props.create(ChartChildActor.class), "ChartChildActor" + Math.random());
            getContext().watch(r);
            router = router.addRoutee(new ActorRefRoutee(r));
         }
         else {
                unhandled(message);
            }
    }

    private void drawBars(NumbersChart chart) throws InterruptedException {
        setup(chart);
        for(int i = 0; i < FREE; i++) {
            router.route(new ChartMessage(chart, i*FREE, WORK), getSelf());
        }
        if(REST != 0) {
            router.route(new ChartMessage(chart, FREE *WORK, REST), getSelf());
        }
    }

    private void setup(NumbersChart chart) {
        FREE = 10;
        WORK = (chart.getMax()-chart.getMin()+1)/ FREE;
        REST = (chart.getMax()-chart.getMin()+1)% FREE;
        createRouters(FREE +1);
    }

    private void createRouters(int n) {
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < n; i++) {
            ActorRef r = getContext().actorOf(Props.create(ChartChildActor.class), "ChartChildActor" + Math.random());
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    private ActorRef getParent() {
        return getContext().parent();
    }
}
