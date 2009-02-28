package JSimPack2.GenericSimulation.AgentBasedSimulation.Exceptions;

public class NoManagerSet extends java.lang.Exception {
    
    public NoManagerSet() {
	this("No manager for this agent was set.");
    }
    
    public NoManagerSet(String msg) {
	super(msg);
    }
}
