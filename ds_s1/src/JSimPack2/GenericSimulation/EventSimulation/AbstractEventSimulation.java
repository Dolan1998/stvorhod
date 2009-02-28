package JSimPack2.GenericSimulation.EventSimulation;

import JSimPack2.GenericSimulation.AbstractGenericSimulation;
import JSimPack2.GenericSimulation.AbstractSimulationReporter;
import JSimPack2.GenericSimulation.SimulationStatus;

public abstract class AbstractEventSimulation extends AbstractGenericSimulation {
    private volatile boolean reporting;
    private java.util.PriorityQueue <AbstractEvent> calendar;
    
    public AbstractEventSimulation() {
        calendar = new java.util.PriorityQueue <AbstractEvent> ();
    }

    public void planEvent(AbstractEvent event) {
	planEvent(event, 0);
    }

    public void planEvent(AbstractEvent event, double occurenceTime) {
        event.setOccurenceTime(getCurrentTime() + occurenceTime);
        calendar.add(event);
    }
    
    public void resetCalendar() {
        setCurrentTime(0);
        calendar.clear();
    }

    public void run() {
	AbstractEvent event;
	
	setStatus(SimulationStatus.running);
	if (getLength() <= 0)
            while (!calendar.isEmpty() && getStatus() != SimulationStatus.interrupting) {
                setCurrentTime((event = calendar.poll()).getOccurenceTime());
                event.execute();
		if (reporting && (event.getReporter() != null))
		    event.getReporter().report(event);
		if (getProgressReporter() != null)
		    getProgressReporter().report(this);
            }
        else
            while (
		getStatus() != SimulationStatus.interrupting
		&& !calendar.isEmpty()
		&& ((event = calendar.poll()).getOccurenceTime()) < getLength()
	    ) {
		setCurrentTime(event.getOccurenceTime());
                event.execute();
		if (reporting && (event.getReporter() != null))
		    event.getReporter().report(event);
		if (getProgressReporter() != null)
		    getProgressReporter().report(this);
	    }
	if (getEndReporter() != null)
	    getEndReporter().report(this);
	if (getStatus() != SimulationStatus.interrupting)
	    setStatus(SimulationStatus.interrupted);
	if (calendar.isEmpty())
	    setStatus(SimulationStatus.completed);
	else
	    setStatus(SimulationStatus.timeCompleted);
        System.gc();
    }
    
    public void setReporting(boolean enabled) {
	reporting = enabled;
    }
}
