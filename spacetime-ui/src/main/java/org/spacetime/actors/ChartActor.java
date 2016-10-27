package org.spacetime.actors;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import org.spacetime.NumbersChart;
import org.spacetime.Triple;

import java.util.Random;

/**
 * Created by zua on 22/10/16.
 */
public class ChartActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof Triple) {
            //System.out.println("Received Message " + ((Pair) message).getFirst() + " for number " + ((Pair) message).getSecond());
            Triple<NumbersChart, Integer, Integer> triple = (Triple<NumbersChart, Integer, Integer>) message;
            NumbersChart chart = triple.getFirst();
            int min = triple.getSecond();
            int max = triple.getThird();
            drawBars(chart, min, max);
        }
    }

    private void drawBars(NumbersChart chart, int start, int end) {
        float[] heights = new float[end-start+1];
        for(int i = 0; i < heights.length; i++) {
            heights[i] = new Random().nextFloat()*100;
        }
        chart.onActorUpdate(start, end, heights);
    }

    private ActorRef getParent() {
        return getContext().parent();
    }
}
