package org.spacetime.actors;

import akka.actor.*;
import akka.japi.Function;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.vaadin.ui.UIDetachedException;
import org.spacetime.math.NumbersChart;
import org.spacetime.utils.Triple;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zua on 22/10/16.
 */
public class Master extends UntypedActor {


    private int REST = 0;
    private int RATE = 100;
    private int FREE;
    private Router router;

    {
        createRouter(1000);
    }

    private void createRouter(int size) {
        REST = size%RATE;
        FREE = size/RATE + ((REST == 0) ? 0: 1);
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < FREE; i++) {
            ActorRef r = getContext().actorOf(Props.create(ChartActor.class));
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }


    private static SupervisorStrategy strategy =
            new OneForOneStrategy(10, Duration.create("1 minute"),
                    new Function<Throwable, SupervisorStrategy.Directive>() {
                        @Override
                        public SupervisorStrategy.Directive apply(Throwable t) {
                            if (t instanceof UIDetachedException) {
                                System.out.println("§§§§§§§§§§§");
                                return SupervisorStrategy.resume();
                            } else {
                                return SupervisorStrategy.escalate();
                            }
                        }
                    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }


    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof NumbersChart) {
            delegateChartDraw((NumbersChart) message);
        }
        else if (message instanceof Terminated) {
            System.out.println("MMMMMMMMMMMMM -> Terminating a child");
            router = router.removeRoutee(((Terminated) message).actor());
            ActorRef r = getContext().actorOf(Props.create(ChartActor.class));
            getContext().watch(r);
            router = router.addRoutee(new ActorRefRoutee(r));
        }

    }

    private void delegateChartDraw(NumbersChart chart) {
        int REST = (chart.getMax() - chart.getMin() +1 ) % FREE;
        int RATE = (chart.getMax() - chart.getMin() +1 ) / FREE;
        createRouter(chart.getMax() - chart.getMin() +1 );
        for (int i = 0; i < FREE; i++) {
            Integer min = i * RATE;
            Integer max = ((i + 1) * RATE) - 1;
            delegatePack(chart, min, max);
            pause(i);
        }
        if(REST != 0) {
            delegatePack(chart, FREE*RATE, FREE*RATE+REST);
        }
    }

    private void delegatePack(NumbersChart chart, Integer min, Integer max) {
        router.route(new Triple<NumbersChart, Integer, Integer>(chart, min, max), getSender());
    }

    private void pause(int i) {
        try {
            Thread.sleep(1000*(FREE-i));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

