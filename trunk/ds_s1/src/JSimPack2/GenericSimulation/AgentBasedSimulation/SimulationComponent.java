package JSimPack2.GenericSimulation.AgentBasedSimulation;

public class SimulationComponent extends Entity {
    private AbstractABASimulation simulation;

    public SimulationComponent(int ID, AbstractABASimulation simulation) {
	super(ID);
	this.simulation = simulation;
    }

    public AbstractABASimulation getSimulation() {
	return simulation;
    }
}
