package JSimPack2.RandomGenerators;

public class BinomialRandom extends AbstractRandom {

    private double[] vyskyty;
    private int pocet = 0;
    private double sucet = 0;

    public BinomialRandom(double[] vyskyty) {
        //this.vyskyty = vyskyty.clone();
        pocet = vyskyty.length;
        this.vyskyty = new double[pocet];
        double pom = 0;
        for (int i = 0; i < vyskyty.length; i++) {
            sucet += vyskyty[i];
        }
        for (int i = 0; i < vyskyty.length; i++) {
            pom += vyskyty[i] / sucet;
            this.vyskyty[i] = pom;
        }
    }

    public BinomialRandom(int[] vyskyty) {
        pocet = vyskyty.length;
        this.vyskyty = new double[pocet];
        double pom = 0;
        for (int i = 0; i < vyskyty.length; i++) {
            sucet += vyskyty[i];
        }
        for (int i = 0; i < vyskyty.length; i++) {
            pom += vyskyty[i] / sucet;
            this.vyskyty[i] = pom;
        }
    }

    public double nextDouble() {
        double h = generator.nextDouble();
        double dh = 0;
        double hh = 0;
        double pomRet = 999;
        for (int i = 0; i < vyskyty.length; i++) {
            dh = hh;
            hh = vyskyty[i];
            if (dh <= h && h < hh) {
                pomRet = i + 1;
            }
        }
        return pomRet;
    }

    public int nextInt() {
        double h = generator.nextDouble();
        double dh = 0;
        double hh = 0;
        int pomRet = 999;
        for (int i = 0; i < vyskyty.length; i++) {
            dh = hh;
            hh = vyskyty[i];
            if (dh <= h && h < hh) {
                pomRet = i + 1;
            }
        }
        return pomRet;
    }
}
