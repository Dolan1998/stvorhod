package JSimPack2.GenericSimulation.AgentBasedSimulation;

import JSimPack2.GenericSimulation.AgentBasedSimulation.Exceptions.NoManagerSet;

public interface IReciever {
    public void processMessage(Message message) throws NoManagerSet ;
}
