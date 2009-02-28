package JSimPack2.RandomGenerators;

import flanagan.math.PsRandom;
import java.util.Random;

public abstract class AbstractRandom {
    protected Random generator;
    protected PsRandom psGen;
    
    public abstract double nextDouble();

    public abstract int nextInt();

    
    public AbstractRandom() {
        generator = new Random();
        psGen = new PsRandom();
    }
}

