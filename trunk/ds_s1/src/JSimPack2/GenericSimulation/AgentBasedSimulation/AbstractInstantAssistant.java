package JSimPack2.GenericSimulation.AgentBasedSimulation;

public abstract class AbstractInstantAssistant extends AbstractAgentComponent {
    public AbstractInstantAssistant(int ID, Agent agent) {
	super(ID, agent);
    }
    
    public abstract void Execute(Message message);
}
