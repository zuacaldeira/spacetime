package org.spacetime.math;

import akka.actor.*;
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

    private ConcurrentLinkedQueue<BarsMessage> bars = new ConcurrentLinkedQueue<>();

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
        initDistributorThred();
        initUpdaterThread();
    }

    private void initDistributorThred() {
        new Thread(()-> {
            ActorSystem system = ActorSystem.create("TS1");
            uiUpdater = system.actorOf(
                    Props.create(
                            UntypedActor.class,
                            () -> { return  getAnonymousActor(); })
            );

            ActorRef worker1 = system.actorOf(Props.create(ChartChildActor.class), "worker1");
            ActorRef worker2 = system.actorOf(Props.create(ChartChildActor.class), "worker2");
            ActorRef worker3 = system.actorOf(Props.create(ChartChildActor.class), "worker3");
            ActorRef worker4 = system.actorOf(Props.create(ChartChildActor.class), "worker4");
            ActorRef worker5 = system.actorOf(Props.create(ChartChildActor.class), "worker5");
            System.out.println("#PARTS = " + PART);
            worker1.tell(new ChartMessage(this, 0, PART), uiUpdater);
            worker2.tell(new ChartMessage(this, PART+1, PART), uiUpdater);
            worker3.tell(new ChartMessage(this, 2 * PART+1, PART), uiUpdater);
            worker4.tell(new ChartMessage(this, 3 * PART + 1, PART), uiUpdater);
            worker5.tell(new ChartMessage(this, 4 * PART+1, REST), uiUpdater);
        }).start();
    }

    private UntypedActor getAnonymousActor() {
        return new UntypedActor() {
            @Override
            public void onReceive(Object message) throws Throwable {
                if (message instanceof BarsMessage) {
                    UI.getCurrent().access(() -> {
                        System.out.println("Anonymous actor RECIEVED MSG " + ((BarsMessage) message).getComponents().length);
                        bars.add((BarsMessage) message);
                    });
                } else {
                    unhandled(message);
                }
            }
        };
    }

    private void initUpdaterThread() {
        new Thread(() -> {
                int done = 0;
                while(done < WORKERS) {
                    if(!bars.isEmpty()) {
                            Component[] comps = bars.peek().getComponents();
                            System.out.println("Found Work Package with size " + comps.length);
                            UI.getCurrent().access(()->{
                                NumbersChart.this.addComponents(comps);
                                setCaption(getCaption(count-=comps.length));
                            });
                        done++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
