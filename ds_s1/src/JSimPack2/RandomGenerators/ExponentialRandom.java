package JSimPack2.RandomGenerators;

public class ExponentialRandom extends AbstractRandom {
    private double mean;

    public ExponentialRandom() {
    }
    
    public ExponentialRandom(double mean) {
        this.mean = mean;
    }

    public double nextDouble() {
        return - mean * Math.log(generator.nextDouble());
    }
    
    public double nextDouble(double mean) {
        return - mean * Math.log(generator.nextDouble());
    }
    
    public void setParameters(double mean) {
	this.mean = mean;
    }

    public int nextInt() {
        return Math.round((float) (- mean * Math.log(generator.nextDouble())));
    }
}
