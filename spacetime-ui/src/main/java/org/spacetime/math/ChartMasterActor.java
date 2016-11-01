package org.spacetime.math;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import org.spacetime.actors.ChartChildActor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zua on 31/10/16.
 */
public class ChartMasterActor extends UntypedActor {


    // Static router initialization
    Router router;
    {
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < 5; i++) {
            ActorRef r = getContext().actorOf(Props.create(ChartChildActor.class));
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    // Actor specifics
    @Override
    public void onReceive(Object message) {
        if (message instanceof ChartMessage) {
            router.route(message, getSender());
        } else if (message instanceof Terminated) {
            router = router.removeRoutee(((Terminated) message).actor());
            ActorRef r = getContext().actorOf(Props.create(Terminated.class));
            getContext().watch(r);
            router = router.addRoutee(new ActorRefRoutee(r));
        }
        else {
            unhandled(message);
        }
    }

}
