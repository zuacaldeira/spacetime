package org.spacetime.math;

/**
 * Created by zua on 29/10/16.
 */
public class ChartMessage {
    private final NumbersChart chart;
    private final int start;
    private final int workload;

    public ChartMessage(NumbersChart chart, int start, int workload) {
        this.chart = chart;
        this.start = start;
        this.workload = workload;
    }

    public NumbersChart getChart() {
        return chart;
    }

    public int getStart() {
        return start;
    }

    public int getWorkload() {
        return workload;
    }
}
