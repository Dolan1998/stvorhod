package JSimPack2.RandomGenerators;

public class TriangularRandom extends AbstractRandom {

    private double a,  b,  m;

    public TriangularRandom(double a, double b, double m) {
        this.a = a;
        this.b = b;
        this.m = m;
    }

    public double nextDouble() {
        double h = generator.nextDouble() * (b - a) + a;

        if (h < m) {
            return Math.sqrt((h - a) * (m - a)) + a;
        } else {
            return b - Math.sqrt((b - h) * (b - m));
        }
    }

    public int nextInt() {
        double h = generator.nextDouble() * (b - a) + a;

        if (h < m) {
            return Math.round((float) (Math.sqrt((h - a) * (m - a)) + a));
        } else {
            return Math.round((float) (b - Math.sqrt((b - h) * (b - m))));
        }
    }
}
