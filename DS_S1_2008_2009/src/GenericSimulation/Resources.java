package GenericSimulation;

import java.util.Iterator;
import java.util.Vector;

public class Resources {
    private int firstFree;
    private Vector <Resource> resources;

    public Resources() {
	this(0);
    }

    public Resources(int count) {
        resources = new Vector <Resource>(count);
        for (int i = 0; i < count; i++)
            resources.setElementAt(new Resource(), i);
    }
    
    public int getSize() {
        return resources.size();
    }

    public int getSeizedCount() {
        return firstFree;
    }

    public int getFreeCount() {
        return resources.size() - firstFree;
    }

    public void setSize(int count) {
	if (count < resources.size())
            do {
                resources.remove(resources.size() - 1);
            } while (count < resources.size());
        else
            while (count > resources.size()) {
                resources.add(new Resource());
            }
        releaseAll();
    }
    
    public double seize(double actualTime) {
        return resources.elementAt(firstFree++).seize(actualTime);
    }
    
    public double release(double actualTime) {
        return resources.elementAt(--firstFree).release(actualTime);
    }
    
    public void releaseAll() {
	for (Resource resource : resources)
            resource.release(0);
        firstFree = 0;
    }
}
