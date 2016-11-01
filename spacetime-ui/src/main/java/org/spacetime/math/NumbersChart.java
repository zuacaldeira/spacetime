package org.spacetime.math;

import akka.actor.*;
import com.sun.org.apache.regexp.internal.RE;
import com.vaadin.ui.*;
import org.spacetime.actors.ChartChildActor;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zua on 22/10/16.
 */
public class NumbersChart extends HorizontalLayout {

    private static final int WORKERS = 4;
    private final int min;
    private final int max;
    private final int PART;
    private final int REST;
    private int count;

    private ActorRef uiUpdater;

    public NumbersChart(int min, int max) {
        this.min = min;
        this.max = max;
        this.count = max - min + 1;
        PART = count / WORKERS;
        REST = count % WORKERS;
        setSizeFull();
        setStyleName("chart");
        setMargin(true);
        setCaption(getCaption(count));
        initBars3();
    }

    private String getCaption(int i) {
        return String.valueOf(i) + "/" + (max-min+1);
    }

    protected void initBars3() {
        new Thread(() -> {
            ActorSystem system = ActorSystem.create("ActorSystem_SPACETIME");
            uiUpdater = system.actorOf(
                    Props.create(
                            UntypedActor.class,
                            () -> { return  getAnonymousActor(); })
            );

            ActorRef master = system.actorOf(Props.create(ChartMasterActor.class), "MASTER");
            master.tell(new ChartMessage(this, 0, PART), uiUpdater);
            master.tell(new ChartMessage(this, PART+1, PART), uiUpdater);
            master.tell(new ChartMessage(this, 2*PART+1, PART), uiUpdater);
            master.tell(new ChartMessage(this, 3*PART+1, PART), uiUpdater);
            if(REST != 0) {
                master.tell(new ChartMessage(this, 4*PART+1, REST), uiUpdater);
            }
        }).start();
    }

    private UntypedActor getAnonymousActor() {
        return new UntypedActor() {
            @Override
            public void onReceive(Object message) throws Throwable {
                if (message instanceof BarsMessage) {
                    UI.getCurrent().access(() -> {
                        System.out.println("Anonymous actor RECIEVED MSG " + ((BarsMessage) message).getComponents().length);
                        initUpdaterThread((BarsMessage) message);
                    });
                } else {
                    unhandled(message);
                }
            }
        };
    }

    private void initUpdaterThread(BarsMessage bars) {
        new Thread(() -> {
            UI.getCurrent().access(() -> {
                addComponents(bars.getComponents());
                setCaption(getCaption(count-=bars.getComponents().length));
            });
        }).start();
    }


    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }
}
