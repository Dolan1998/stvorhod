package JSimPack2.GenericSimulation;

public abstract class AbstractGenericSimulation extends Thread {
    private volatile SimulationStatus status;
    private AbstractSimulationReporter progressReporter, endReporter;
    private double
	    currentTime = 0,
	    simulationLength;
    
    public AbstractGenericSimulation() {
        System.out.println("JSimPack (Java Simulation Package) created by Jozo Kvasnicak.\n");
    }

    public double getCurrentTime() {
        return currentTime;
    }

    protected AbstractSimulationReporter getEndReporter() {
	return endReporter;
    }
    
    public SimulationStatus getStatus() {
	return status;
    }

    public double getLength() {
	return simulationLength;
    }

    protected AbstractSimulationReporter getProgressReporter() {
	return progressReporter;
    }
    
    protected void setCurrentTime(double currentTime) {
	this.currentTime = currentTime;
    }

    public void setEndReporter(AbstractSimulationReporter reporter) {
	this.endReporter = reporter;
    }
    
    public void interrupt() {
	status = SimulationStatus.interrupting;
    }
    
    public abstract void onFinish();
    
    public void reset() {
	currentTime = 0;
    }

    public void setLength(double simulationLength) {
	this.simulationLength = simulationLength;
    }

    public void setProgressReporter(AbstractSimulationReporter reporter) {
	this.progressReporter = reporter;
    }

    public void setStatus(SimulationStatus status) {
	this.status = status;
    }
    
    public static String timeToString(double time) {
        String hour, minute, second;
        
        hour = String.valueOf(Math.round(Math.floor(time / 3600)));
        minute = String.valueOf(Math.round(Math.floor(time % 3600)) / 60);
        if (minute.length() < 2)
            minute = "0" + minute;
        second = String.valueOf(Math.round(time % 60));
        if (second.length() < 2)
            second = "0" + second;
        
        return hour + ":" + minute + ":" + second;
    }
}
