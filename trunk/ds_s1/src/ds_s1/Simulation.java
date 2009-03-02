/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_s1;

import JSimPack2.GenericSimulation.SimulationStatus;
import JSimPack2.RandomGenerators.AbstractRandom;
import JSimPack2.RandomGenerators.EmpiricalRandom;
import JSimPack2.RandomGenerators.UniformRandom;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author Klesako
 */
public class Simulation extends Thread {
    private boolean  superspeed = false;

    private Ds_s1View view;
    private SimulationStatus status = SimulationStatus.interrupted;
    private int countCycles = 10000; // count cycles
    private ArrayList<AbstractRandom> generators = new ArrayList<AbstractRandom>();
    private ArrayList<Integer> activities = new ArrayList<Integer>();
    private XYSeriesCollection seriesCollection;
    private int replication = 0;
    private double avgSum; // sum to average
    private double sumOfCompleted45;

    public ArrayList<Integer> getActivities() {
        return activities;
    }

    public void setCountCycles(int countCycles) {
        this.countCycles = countCycles;
    }

    public void setSuperspeed(boolean superspeed) {
        this.superspeed = superspeed;
    }

    public Simulation(Ds_s1View view) {
        this.view = view;
        for (int i = 0; i <= 9; i++) {
            activities.add(0);
            generators.add(null);
        }

        int[] probabilities2 = {2, 7, 1};
        int[] minValues2 = {8, 10, 12};
        int[] maxValues2 = {8, 10, 12};

        int[] probabilities5 = {1, 6, 3};
        int[] minValues5 = {3, 6, 8};
        int[] maxValues5 = {5, 7, 9};

        int[] probabilities8 = {3, 5, 2};
        int[] minValues8 = {8, 9, 12};
        int[] maxValues8 = {8, 9, 12};


        generators.set(1, new UniformRandom(5, 15));
        generators.set(2, new EmpiricalRandom(probabilities2, minValues2, maxValues2));
        generators.set(3, new UniformRandom(3, 5));
        generators.set(4, new UniformRandom(7, 11));
        generators.set(5, new EmpiricalRandom(probabilities5, minValues5, maxValues5));
        generators.set(6, new UniformRandom(10, 14));
        generators.set(7, new UniformRandom(3, 3));
        generators.set(8, new EmpiricalRandom(probabilities8, minValues8, maxValues8));
        generators.set(9, new UniformRandom(14, 16));

        init();
    }

    public void init() {
        XYSeries series = new XYSeries("Histogram");
        seriesCollection = new XYSeriesCollection(series);

        // create plot...
        NumberAxis xAxis = new NumberAxis("Weeks");
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis("Count");
        yAxis.setAutoRangeIncludesZero(false);

        XYSplineRenderer renderer1 = new XYSplineRenderer();
        XYPlot plot = new XYPlot(seriesCollection, xAxis, yAxis, renderer1);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(4, 4, 4, 4));

        // create and return the chart panel...
        JFreeChart chart = new JFreeChart("Histogram of weeks", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ChartUtilities.applyCurrentTheme(chart);
        ChartPanel chartPanel = new ChartPanel(chart, false);

        view.pnlHistogram.removeAll();
        view.pnlHistogram.add(chartPanel);
        view.pnlHistogram.getComponent(0).setSize(view.pnlHistogram.getSize());

        replication = 0;
        view.lblReplikacia.setText(String.valueOf(replication) + "/" + String.valueOf(countCycles));
        avgSum = 0;
        sumOfCompleted45 = 0;
    }

    private void generateActivities() {
        for (int i = 1; i <= 9; i++) {
            activities.set(i, generators.get(i).nextInt());
        }
    }

    public int simulate() {
        generateActivities();
        int cas = 0;
        cas += activities.get(1);
        int pom = activities.get(2) + activities.get(6) + activities.get(5);
        if ((activities.get(3) + activities.get(4) + activities.get(5)) > pom) {
            pom = activities.get(3) + activities.get(4) + activities.get(5);
        }
        if ((activities.get(3) + activities.get(7) + activities.get(8)) > pom) {
            pom = activities.get(3) + activities.get(7) + activities.get(8);
        }
        cas += pom;
        cas += activities.get(9);
        //histogram[cas]++;
        return cas;
    }

    @Override
    public void run() {
        status = SimulationStatus.running;
        double cas;
        while(status == SimulationStatus.running && replication < countCycles){
            //view.tarNotes.setText(String.valueOf(replication) + "\n" +  view.tarNotes.getText());
            cas = simulate();
            avgSum += cas;
            sumOfCompleted45 += cas <= 45 ? 1 : 0;
            if(!superspeed){
                view.lblReplikacia.setText(String.valueOf(replication + 1) + "/" + String.valueOf(countCycles));
                if (seriesCollection.getSeries(0).indexOf(cas) < 0) {
                    seriesCollection.getSeries(0).add(cas, 1);
                } else {
                    seriesCollection.getSeries(0).update(cas, seriesCollection.getSeries(0).getY(seriesCollection.getSeries(0).indexOf(cas)).doubleValue() + 1d);
                }
            }
            replication++;
        }
        // vypisanie vysledkov
        if(replication >= countCycles){
            view.tarNotes.setText("Finished " + String.valueOf(replication) + " replications." + "\n" +  view.tarNotes.getText());
            view.tarNotes.setText("Average is: " + String.valueOf((double)(avgSum / countCycles)) + "\n" +  view.tarNotes.getText());
            view.tarNotes.setText("45 weeks: " + String.valueOf((double)(100 * sumOfCompleted45 / countCycles)) + "%\n" +  view.tarNotes.getText());
            view.btnStart.setEnabled(false);
            view.btnStop.setEnabled(false);
            view.btnReset.setEnabled(true);
        }
    }

    @Override
    public void interrupt() {
        status = SimulationStatus.interrupting;
    }
}
