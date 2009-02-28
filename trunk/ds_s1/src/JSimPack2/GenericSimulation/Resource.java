package JSimPack2.GenericSimulation;

public class Resource {
    private boolean seized = false;
    private double statusChangeTime = 0;
    
    public Resource() {
    }

    public double seize(double actualTime) {
	double statusDuration = actualTime - statusChangeTime;
	
        seized = true;
        statusChangeTime = actualTime;
        
        return statusDuration;
    }
    
    public double release(double actualTime) {
	double statusDuration = actualTime - statusChangeTime;
	
        seized = false;
        statusChangeTime = actualTime;
        
        return statusDuration;
    }
}
