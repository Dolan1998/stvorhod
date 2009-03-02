/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSimPack2.RandomGenerators;

/**
 *
 * @author Klesako
 */
public class UniformRandom extends AbstractRandom {

    private double min, max;
    private double offset, length;

    public UniformRandom(double min, double max) {
        this.min = min;
        this.max = max;
        this.offset = min;
        this.length = max - min + 1;
    }


    public int nextInt() {
        return (int)Math.round(generator.nextInt((int)Math.round(length)) + offset);
    }

    @Override
    public double nextDouble() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
