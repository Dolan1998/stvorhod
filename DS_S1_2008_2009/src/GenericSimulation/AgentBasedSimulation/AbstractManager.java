package GenericSimulation.AgentBasedSimulation;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractManager extends AbstractMessenger {
    private LinkedList<Integer>
	    delimitedMessages,
	    ownMessages;

    public AbstractManager(int ID, Agent agent) {
	super(ID, agent);
	delimitedMessages = new LinkedList<Integer>();
	ownMessages = new LinkedList<Integer>();
    }
    
    public void addDelimitedMessage(int messageCode) {
	delimitedMessages.add(new Integer(messageCode));
    }
    
    public void addOwnMessage(int messageCode) {
	ownMessages.add(new Integer(messageCode));
    }
    
    public abstract Agent delimit(Message message);
    
    public boolean delimitingMessage(Message message) {
	for (Iterator it = delimitedMessages.iterator(); it.hasNext();) {
	    Message i = (Message) it.next();
	    if (i.getCode() == message.getCode())
		return true;
	}
	return false;
    }

    public void notice(Message message) {
	message.setType(MessageType.notice);
	message.setDeliveryTime(getSimulation().getCurrentTime());
	message.setSender(this);
	getSimulation().sendMessage(message);
    }
    
    public void noticeNotify(Message message) {
	message.setType(MessageType.noticeNotify);
	message.setDeliveryTime(getSimulation().getCurrentTime());
	message.setSender(this);
	getSimulation().sendMessage(message);
    }
    
    public boolean processingMessage(Message message) {
	for (Iterator<Integer> it = ownMessages.iterator(); it.hasNext();) {
	    Integer i = it.next();
	    if (i == message.getCode())
		return true;
	}
	return false;
    }
    
    public void request(Message message) {
	message.setType(MessageType.request);
	message.setDeliveryTime(getSimulation().getCurrentTime());
	message.setSender(this);
	getSimulation().sendMessage(message);
    }
    
    public void response(Message message) {
	message.setType(MessageType.response);
	message.setDeliveryTime(getSimulation().getCurrentTime());
	message.setSender(this);
	getSimulation().sendMessage(message);
    }
    
    public void startAssistant(Message message) {
	message.setType(MessageType.start);
	message.setDeliveryTime(getSimulation().getCurrentTime());
	message.setSender(this);
	getSimulation().sendMessage(message);
    }
}
