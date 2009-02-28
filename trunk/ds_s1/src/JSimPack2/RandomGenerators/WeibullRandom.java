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
public class WeibullRandom extends AbstractRandom {
    private double offset;
    private double beta;
    private double alfa;

    public WeibullRandom(double offset, double beta, double alfa) {
        this.offset = offset;
        this.beta = beta;
        this.alfa = alfa;
    }



    public double nextDouble() {
        return psGen.nextWeibull(offset, beta, alfa);
    }
    

    public int nextInt() {
        return (int) Math.round(psGen.nextWeibull(offset, beta, alfa));
    }
}