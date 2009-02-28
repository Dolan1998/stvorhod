package RandomGenerators;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public abstract class AbstractRandom {
    protected Random generator;
    
    public abstract double nextDouble();
//    public abstract float nextFloat();
    public abstract int nextInt();
//    public abstract long nextLong();
    
    public AbstractRandom() {
	//generator = new Random((new GregorianCalendar()).getTimeInMillis());
        generator = new Random();
    }
}

