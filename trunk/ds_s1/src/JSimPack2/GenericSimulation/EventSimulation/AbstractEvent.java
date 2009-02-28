package JSimPack2.GenericSimulation.EventSimulation;

public abstract class AbstractEvent implements Comparable <AbstractEvent> {
    private double occurenceTime;
    private AbstractEventReporter reporter;
    private AbstractEventSimulation simulation;
    
    public int compareTo(AbstractEvent anotherEvent) {
        return Math.round(Math.signum(Double.compare(occurenceTime, anotherEvent.getOccurenceTime())));
    }
    
    public abstract void execute();
    
    public double getOccurenceTime() {
        return occurenceTime;
    }
    
    public AbstractEventReporter getReporter() {
	return reporter;
    }
    
    public AbstractEventSimulation getSimulation() {
        return simulation;
    }
    
    public void plan() {
        simulation.planEvent(this);
    }
    
    public void plan(double occurenceTime) {
        simulation.planEvent(this, occurenceTime);
    }
    
    public void setOccurenceTime(double occurenceTime) {
        this.occurenceTime = occurenceTime;
    }
    
    public void setReporter(AbstractEventReporter reporter) {
	this.reporter = reporter;
    }
    
    public void setSimulation(AbstractEventSimulation simulation) {
        this.simulation = simulation;
    }
}