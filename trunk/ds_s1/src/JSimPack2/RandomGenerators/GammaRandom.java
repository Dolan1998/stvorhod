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
public class GammaRandom extends AbstractRandom {
    private double offset;
    private double alfa; // shape
    private double beta; // scale

    public GammaRandom(double offset, double alfa, double beta) {
        this.offset = offset;
        this.alfa = alfa;
        this.beta = beta;
    }

    public double nextDouble() {
        return psGen.nextGamma(alfa, offset, beta);
    }
    
    public int nextInt() {
        return (int) Math.round(psGen.nextGamma(beta, offset, alfa));
    }
}