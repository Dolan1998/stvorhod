package GenericSimulation.AgentBasedSimulation;

import GenericSimulation.AgentBasedSimulation.Exceptions.NoManagerSet;

public interface IReciever {
    public void processMessage(Message message) throws NoManagerSet ;
}
