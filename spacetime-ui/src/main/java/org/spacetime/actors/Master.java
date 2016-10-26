package org.spacetime;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zua on 22/10/16.
 */
public class Master extends UntypedActor {

    Router router;
    private int free = 100;

    {
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < free; i++) {
            ActorRef r = getContext().actorOf(Props.create(ChartActor.class));
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof NumbersChart) {
            delegateChartDraw((NumbersChart) message);
        }
        else if (message instanceof Terminated) {
            router = router.removeRoutee(((Terminated) message).actor());
            ActorRef r = getContext().actorOf(Props.create(ChartActor.class));
            getContext().watch(r);
            router = router.addRoutee(new ActorRefRoutee(r));
        }

    }

    private void delegateChartDraw(NumbersChart message) {
        int rate = (message.getMax() - message.getMin())/free;
        for(int i = 0; i < free; i++) {
            router.route(new Triple<NumbersChart, Integer, Integer>(message, i*rate, i*rate+free), getSender());
            //Thread.sleep(2000);
        }
    }
}
