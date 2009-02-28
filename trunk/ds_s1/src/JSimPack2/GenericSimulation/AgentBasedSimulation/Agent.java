package JSimPack2.GenericSimulation.AgentBasedSimulation;

import JSimPack2.GenericSimulation.AgentBasedSimulation.Exceptions.NoManagerSet;
import JSimPack2.GenericSimulation.AgentBasedSimulation.Exceptions.TooManyBossesException;
import java.util.Iterator;
import java.util.LinkedList;

public class Agent extends SimulationComponent implements IReciever {
    private LinkedList<Agent> inferiors;
    private AbstractManager manager;
    private Agent superior;
    
    public Agent(int ID, AbstractABASimulation simulation, Agent superior) throws TooManyBossesException {
	super(ID, simulation);
	inferiors = new LinkedList<Agent>();
	this.superior = superior;
	if (superior != null)
	    superior.addInferiorAgent(this);
	else
	    getSimulation().setBoss(this);
    }
    
    private void addInferiorAgent(Agent inferiorAgent) {
	inferiors.add(inferiorAgent);
    }
    
    public void addDelimitedMessage(int messageCode) {
	manager.addDelimitedMessage(messageCode);
    }
    
    public void addOwnMessage(int messageCode) throws NoManagerSet {
	if (manager != null)
	    manager.addOwnMessage(messageCode);
	else
	    throw new NoManagerSet();
    }
    
    public Agent findProcessor(Message message) {
	Agent agent;
	
	if (manager != null) {
	    if (manager.processingMessage(message))
		return this;
	    if (manager.delimitingMessage(message))
		return manager.delimit(message);
	}
	for (Iterator<Agent> iterator = inferiors.iterator(); iterator.hasNext();) {
	    agent = iterator.next().findProcessor(message);
	    if (agent != null)
		return agent;
	}
	return null;
    }

    public AbstractManager getManager() {
	return manager;
    }
    
    public final void processMessage(Message message) throws NoManagerSet {
	if (manager != null)
	    manager.processMessage(message);
	else {
            System.out.println(message.toString());
	    throw new NoManagerSet();
        }
    }
    
    public void setManager(AbstractManager manager) {
	this.manager = manager;
    }
}
