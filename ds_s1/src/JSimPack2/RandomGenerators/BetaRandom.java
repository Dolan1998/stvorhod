/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JSimPack2.RandomGenerators;

import flanagan.math.PsRandom;

/**
 *
 * @author R
 */
public class BetaRandom extends AbstractRandom {
    private double alfa1;
    private double alfa2;
    private double min;
    private double max;

    public BetaRandom(double alfa1, double alfa2, double min, double max) {
        this.alfa1 = alfa1;
        this.alfa2 = alfa2;
        this.min = min;
        this.max = max;
    }

    public double nextDouble() {
        return psGen.nextBeta(min, max, alfa1, alfa2);
    }
    

    public int nextInt() {
        return (int) Math.round(psGen.nextBeta(min, max, alfa1, alfa2));
    }
}