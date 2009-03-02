package JSimPack2.RandomGenerators;

public class EmpiricalRandom extends AbstractRandom {
    private double[] probabilities;
    private double[] minValues;
    private double[] maxValues;

    public EmpiricalRandom(double[] probabilities, double[] minValues, double[] maxValues) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public EmpiricalRandom(int[] probabilities, int[] minValues, int[] maxValues) {
        this.probabilities = new double[probabilities.length];
        this.minValues = new double[probabilities.length];
        this.maxValues = new double[probabilities.length];
        this.probabilities[0] = probabilities[0];
        for (int i = 1; i < probabilities.length; i++) {
            this.probabilities[i] = this.probabilities[i - 1] + probabilities[i];
        }
        for (int i = 0; i < probabilities.length; i++) {
            this.minValues[i] = minValues[i];
            this.maxValues[i] = maxValues[i];
        }
    }

    public double nextDoubleAlt() {
        double h = generator.nextDouble() * probabilities[probabilities.length - 1];

        if (h <= probabilities[0]) {
            return 0;
        }
        for (int i = 1; i < probabilities.length - 1; i++) {
            if (h > probabilities[i - 1] && h <= probabilities[i]) {
                return i;
            }
        }
        return probabilities.length - 1;
    }

    public int nextIntAlt() {
        double h = generator.nextDouble() * probabilities[probabilities.length - 1];

        if (h <= probabilities[0]) {
            return 0;
        }
        for (int i = 1; i < probabilities.length - 1; i++) {
            if (h > probabilities[i - 1] && h <= probabilities[i]) {
                return i;
            }
        }
        return probabilities.length - 1;
    }

    public int nextInt() {
        int index = -1;
        double h = generator.nextDouble() * probabilities[probabilities.length - 1];
        if (h < probabilities[0]) {
            index = 0;
        }
        else{
            for (int i = 1; i < probabilities.length - 1; i++) {
                if (h >= probabilities[i - 1] && h < probabilities[i]) {
                    index = i;
                    break;
                }
            }
            if(index == -1){
                index = probabilities.length - 1;
            }
        }
        UniformRandom uniformRandom = new UniformRandom(minValues[index], maxValues[index]);
        return uniformRandom.nextInt();
    }

    @Override
    public double nextDouble() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
