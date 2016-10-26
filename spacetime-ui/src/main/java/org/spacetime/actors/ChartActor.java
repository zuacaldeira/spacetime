package org.spacetime;

import akka.actor.UntypedActor;

import java.util.Random;

/**
 * Created by zua on 22/10/16.
 */
public class ChartActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof Pair) {
            //System.out.println("Received Message " + ((Pair) message).getFirst() + " for number " + ((Pair) message).getSecond());
            Pair<NumbersChart, Integer> pair = (Pair<NumbersChart, Integer>) message;
            drawBar(pair.getFirst(), pair.getSecond());
        }

        if(message instanceof Triple) {
            //System.out.println("Received Message " + ((Pair) message).getFirst() + " for number " + ((Pair) message).getSecond());
            Triple<NumbersChart, Integer, Integer> triple = (Triple<NumbersChart, Integer, Integer>) message;
            for(int i = triple.getSecond(); i <= triple.getThird(); i++) {
                drawBar(triple.getFirst(), triple.getSecond());
                Thread.sleep(1000);
            }
        }
    }

    private void drawBar(NumbersChart message, int i) {
        message.onActorUpdate(i, new Random().nextFloat()*100);
    }
}
