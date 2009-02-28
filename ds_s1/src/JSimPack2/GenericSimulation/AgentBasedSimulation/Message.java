package JSimPack2.GenericSimulation.AgentBasedSimulation;

public class Message implements Comparable<Message> {
    private IReciever addressee;
    private int code;
    private double deliveryTime;
    private MessageType type;
    private AbstractABASimulation simulation;
    private SimulationComponent sender;
    
    public Message(int code, AbstractABASimulation simulation) {
	this.code = code;
	this.simulation = simulation;
	deliveryTime = simulation.getCurrentTime();
	type = MessageType.notDefined;
    }
    
    public int compareTo(Message anotherMessage) {
	if (deliveryTime < anotherMessage.getDeliveryTime())
	    return -1;
	else if (deliveryTime > anotherMessage.getDeliveryTime())
	    return 1;
	else if (code < anotherMessage.getCode())
	    return -1;
	else if (code > anotherMessage.getCode())
	    return 1;
	return 0;
    }
    
    public IReciever getAddressee() {
	return addressee;
    }
    
    public int getCode() {
	return code;
    }
    
    public double getDeliveryTime() {
	return deliveryTime;
    }
    
    public SimulationComponent getSender() {
	return sender;
    }
    
    public MessageType getType() {
	return type;
    }
    
    public void setAddressee(IReciever addressee) {
	this.addressee = addressee;
    }
    
    public void setCode(int code) {
	this.code = code;
    }
    
    public void setDeliveryTime(double deliveryTime) {
	this.deliveryTime = deliveryTime;
    }
    
    public void setSender(SimulationComponent sender) {
	this.sender = sender;
    }

    public void setType(MessageType type) {
	this.type = type;
    }
    
    public String toString() {
	return "sender: " + sender.toString() + ", addressee: " + ((addressee != null) ? addressee.toString() : "null") + ", code: " + String.valueOf(code) + ", delivery-time: " + String.valueOf(deliveryTime) + ", message-type: " + type.toString();
    }
}
