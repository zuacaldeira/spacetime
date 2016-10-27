package org.spacetime.actors;

import akka.actor.*;
import akka.japi.Function;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.vaadin.ui.UIDetachedException;
import org.spacetime.NumbersChart;
import org.spacetime.Triple;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zua on 22/10/16.
 */
public class Master extends UntypedActor {


    private static final int PROBLEM_SIZE = 1000;
    private Router router;
    private int free;

    {
        createRouter(2);
    }



    private void createRouter(int size) {
        free = size;
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i <= free; i++) {
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

    private void delegateChartDraw(NumbersChart message) {
        int rate = (message.getMax() - message.getMin()) / free;
        int rest = (message.getMax() - message.getMin()) % free;
        for (int i = 0; i < free; i++) {
            Integer min = i * rate;
            Integer max = ((i + 1) * rate) - 1;
            Triple<NumbersChart, Integer, Integer> work = (i == free - 1)
                    ? new Triple<NumbersChart, Integer, Integer>(message, min, max + rest)
                    : new Triple<NumbersChart, Integer, Integer>(message, min, max);
            router.route(work, getSender());
            //pause();
        }
    }

    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

