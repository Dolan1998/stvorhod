package JSimPack2.StatisticLists;

import java.util.Iterator;
import java.util.LinkedList;

public class StatisticList {
    private LinkedList <Double> list;
    
    public StatisticList() {
        list = new LinkedList <Double>();
    }
    
    public void addValue(double value) {
        list.addLast(new Double(value));
    }
    
    public void clear() {
        list.clear();
    }
    
    public double getMean() {
        double sum = 0;
        
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Double element = (Double) iterator.next();
            
            sum += element.doubleValue();
        }
        return sum / list.size();
    }
}
