package GenericSimulation.AgentBasedSimulation;

import GenericSimulation.AgentBasedSimulation.IReciever;

public abstract class AbstractMessenger extends AbstractAgentComponent implements IReciever {
    public AbstractMessenger(int ID, Agent agent) {
	super(ID, agent);
    }
    public abstract void notice(Message message);
}
