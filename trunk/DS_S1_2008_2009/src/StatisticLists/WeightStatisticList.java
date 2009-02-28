package StatisticLists;

import java.util.Iterator;
import java.util.LinkedList;

public class WeightStatisticList {
    private LinkedList <WeightStatisticListNode> list;
    
    public WeightStatisticList() {
        list = new LinkedList <WeightStatisticListNode>();
    }
    
    public void addValue(double value, double weight) {
        list.addLast(new WeightStatisticListNode(value, weight));
    }
        
    public void clear() {
        list.clear();
    }

    public double getMean() {
        double valueSum = 0, weightSum = 0;
        
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            WeightStatisticListNode element = (WeightStatisticListNode) iterator.next();
            
            valueSum += element.value * element.weight;
	    weightSum += element.weight;
        }
        return valueSum / weightSum;
    }
}

final class WeightStatisticListNode {
    public double value = 0, weight = 0;
    
    public WeightStatisticListNode(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }
}
