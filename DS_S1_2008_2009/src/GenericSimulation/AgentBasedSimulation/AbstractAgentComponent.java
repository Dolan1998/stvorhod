package GenericSimulation.AgentBasedSimulation;

public class AbstractAgentComponent extends SimulationComponent {
    private Agent agent;
    
    public AbstractAgentComponent(int ID, Agent agent) {
	super(ID, agent.getSimulation());
	this.agent = agent;
    }
    
    public Agent getAgent() {
	return agent;
    }
}
