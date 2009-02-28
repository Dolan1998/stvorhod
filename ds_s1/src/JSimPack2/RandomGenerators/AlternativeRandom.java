package JSimPack2.RandomGenerators;

public class AlternativeRandom extends AbstractRandom {
    private double[] probabilities;
    
    public AlternativeRandom() {
    }
    
    public AlternativeRandom(double[] probabilities) {
        this.probabilities = probabilities.clone();
	for (int i = 1; i < probabilities.length; i++)
	    probabilities[i] += probabilities[i-1];
    }
    
    public AlternativeRandom(int[] probabilities) {
        this.probabilities = new double[probabilities.length];
	this.probabilities[0] = probabilities[0];
	for (int i = 1; i < probabilities.length; i++)
	    this.probabilities[i] += probabilities[i-1];
    }
    
    public double nextDouble() {
	double h = generator.nextDouble() * probabilities[probabilities.length - 1];
	
	if (h <= probabilities[0])
	    return 0;
	for (int i = 1; i < probabilities.length - 1; i++) {
	    if (h > probabilities[i-1] && h <= probabilities[i])
		return i;
	}
	return probabilities.length - 1;
    }

    public int nextInt() {
	double h = generator.nextDouble() * probabilities[probabilities.length - 1];
	
	if (h <= probabilities[0])
	    return 0;
	for (int i = 1; i < probabilities.length - 1; i++) {
	    if (h > probabilities[i-1] && h <= probabilities[i])
		return i;
	}
	return probabilities.length - 1;
    }

    public int nextInt(int[] probabilities) {
	float sum = 0, h;
	
	for (int i = 0; i < probabilities.length; i++)
	    sum += probabilities[i];
	h = generator.nextFloat() * sum;
	sum = 0;
	for (int i = 0; i < probabilities.length; i++) {
	    if (h < probabilities[i] + sum)
		return i;
	    else
		sum += probabilities[i];
	}
	return -1;
    }
    
    public void setParameters(double[] probabilities) {
        this.probabilities = probabilities.clone();
	for (int i = 1; i < probabilities.length; i++)
	    probabilities[i] += probabilities[i-1];
    }
    
    public void setParameters(int[] probabilities) {
        this.probabilities = new double[probabilities.length];
	this.probabilities[0] = probabilities[0];
	for (int i = 1; i < probabilities.length; i++)
	    probabilities[i] += probabilities[i-1];	
    }
}
