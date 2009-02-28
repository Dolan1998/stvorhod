package GenericSimulation.AgentBasedSimulation;

public abstract class AbstractContinualAssistant extends AbstractMessenger {
    public AbstractContinualAssistant(int ID, Agent agent) {
	super(ID, agent);
    }
    
    public void assistantFinished(Message message) {
	message.setType(MessageType.finish);
	message.setAddressee(getAgent());
	message.setDeliveryTime(getSimulation().getCurrentTime());
	message.setSender(this);
	getSimulation().sendMessage(message);
    }
    
    public void hold(double duration, Message message) {
	message.setType(MessageType.hold);
	message.setDeliveryTime(getSimulation().getCurrentTime() + duration);
	message.setSender(this);
	getSimulation().sendMessage(message);
    }
    
    public void notice(Message message) {
	message.setType(MessageType.notice);
	message.setDeliveryTime(getSimulation().getCurrentTime());
	message.setSender(this);
	getSimulation().sendMessage(message);
    }
}

