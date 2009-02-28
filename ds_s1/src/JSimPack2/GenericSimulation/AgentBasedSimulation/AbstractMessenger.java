package JSimPack2.GenericSimulation.AgentBasedSimulation;

import JSimPack2.GenericSimulation.AgentBasedSimulation.IReciever;

public abstract class AbstractMessenger extends AbstractAgentComponent implements IReciever {
    public AbstractMessenger(int ID, Agent agent) {
	super(ID, agent);
    }
    public abstract void notice(Message message);
}
