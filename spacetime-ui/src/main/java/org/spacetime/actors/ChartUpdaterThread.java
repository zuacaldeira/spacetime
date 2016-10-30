package org.spacetime.actors;

import org.spacetime.math.NumbersChart;

import java.util.Random;

/**
 * Created by zua on 29/10/16.
 */
public class ChartUpdaterThread extends Thread {
    private volatile NumbersChart chart;

    public ChartUpdaterThread(NumbersChart chart) {
        this.chart = chart;
    }

}
