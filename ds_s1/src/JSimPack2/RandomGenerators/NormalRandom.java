package JSimPack2.RandomGenerators;

public class NormalRandom extends AbstractRandom {
    private double mean, stdev;
    
    public NormalRandom() {
    }
    
    public NormalRandom(double mean, double variance) {
	this.mean = mean;
	this.stdev = Math.sqrt(variance);
    }

    public double nextDouble() {
	return stdev * generator.nextGaussian() + mean;
    }

    public int nextInt() {
	return Math.round((float) (stdev * generator.nextGaussian() + mean));
    }
    
    public void setParameters(double mean, double variance) {
	this.mean = mean;
	this.stdev = Math.sqrt(variance);
    }
}
