package JSimPack2.RandomGenerators;

public class AlternativeRandom extends AbstractRandom {

    private double[] probabilities;

    public AlternativeRandom(double[] probabilities) {
        this.probabilities = new double[probabilities.length];
        this.probabilities[0] = probabilities[0];
        for (int i = 1; i < probabilities.length; i++) {
            this.probabilities[i] = this.probabilities[i - 1] + probabilities[i];
        }
    }

    public AlternativeRandom(int[] probabilities) {
        this.probabilities = new double[probabilities.length];
        this.probabilities[0] = probabilities[0];
        for (int i = 1; i < probabilities.length; i++) {
            this.probabilities[i] = this.probabilities[i - 1] + probabilities[i];
        }
    }

    public double nextDouble() {
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
}
