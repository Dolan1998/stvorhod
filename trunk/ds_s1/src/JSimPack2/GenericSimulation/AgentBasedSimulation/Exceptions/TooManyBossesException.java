package JSimPack2.GenericSimulation.AgentBasedSimulation.Exceptions;

public class TooManyBossesException extends java.lang.Exception {
    public TooManyBossesException() {
	this("Just 1 boss agent alowed!");
    }
    
    public TooManyBossesException(String msg) {
	super(msg);
    }
}
