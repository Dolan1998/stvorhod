package JSimPack2.RandomGenerators;

public class ExponentialRandom extends AbstractRandom {
    private double offset;
    private double mean;

    public ExponentialRandom(double offset, double mean) {
        this.offset = offset;
        this.mean = mean;
    }

    public double nextDouble() {
        return - mean * Math.log(generator.nextDouble()) + offset;
    }

    public int nextInt() {
        return (int) Math.round((- mean * Math.log(generator.nextDouble()) + offset));
    }
}
