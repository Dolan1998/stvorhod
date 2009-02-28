package JSimPack2.GenericSimulation.AgentBasedSimulation;

import JSimPack2.GenericSimulation.AbstractGenericSimulation;
import JSimPack2.GenericSimulation.AgentBasedSimulation.Exceptions.NoManagerSet;
import JSimPack2.GenericSimulation.AgentBasedSimulation.Exceptions.TooManyBossesException;
import JSimPack2.GenericSimulation.SimulationStatus;

public abstract class AbstractABASimulation extends AbstractGenericSimulation {
    private Agent boss;
    private java.util.PriorityQueue<Message> messageBox;

    public AbstractABASimulation() {
        messageBox = new java.util.PriorityQueue <Message> ();
    }

    public Agent getBoss() {
	return boss;
    }
    
    public void reset() {
	super.reset();
	messageBox.clear();
    }
    
    public void sendMessage(Message message) {
//	if (message.getAddressee() == null)
//	    System.out.println("null");
        messageBox.add(message);;
    }
    
    public void setBoss(Agent agent) throws TooManyBossesException {
	if (boss != null)
	    throw new TooManyBossesException();
	this.boss = agent;
    }
    
    public void resetCalendar() {
        setCurrentTime(0);
        messageBox.clear();
    }

    public void run() {
	Message message;
	
	setStatus(SimulationStatus.running);
	if (getLength() <= 0)
            while (!messageBox.isEmpty() && getStatus() != SimulationStatus.interrupting) {
		if (getCurrentTime() != (message = messageBox.poll()).getDeliveryTime()) {
		    setCurrentTime(message.getDeliveryTime());
		    if (getProgressReporter() != null)
			getProgressReporter().report(this);
		}
                if (message.getAddressee() != null)
		    try {
			    message.getAddressee().processMessage(message);
		    } catch (NoManagerSet ex) {
			    ex.printStackTrace();
		    }
		else
		    try {
			IReciever reciever = boss.findProcessor(message);
			if (reciever != null)
			    reciever.processMessage(message);
			else {
			    System.out.println("******* Unprocessed message! *******");
			    System.out.println(message.toString());
			}
		    } catch (NoManagerSet ex) {
			    ex.printStackTrace();
		    }
	    }
        else
            while (
		getStatus() != SimulationStatus.interrupting
		&& !messageBox.isEmpty()
		&& ((message = messageBox.poll()).getDeliveryTime()) < getLength()
	    ) {
		if (getCurrentTime() != message.getDeliveryTime()) {
		    setCurrentTime(message.getDeliveryTime());
		    if (getProgressReporter() != null)
			getProgressReporter().report(this);
		}
                if (message.getAddressee() != null)
		    try {
			    message.getAddressee().processMessage(message);
		    } catch (NoManagerSet ex) {
			    ex.printStackTrace();
		    }
		else
		    try {
			IReciever reciever = boss.findProcessor(message);
			if (reciever != null)
			    reciever.processMessage(message);
			else {
			    System.out.println("******* Unprocessed message! *******");
			    System.out.println(message.toString());
			}
		    } catch (NoManagerSet ex) {
			    ex.printStackTrace();
		    }
	    }
	if (getEndReporter() != null)
	    getEndReporter().report(this);
	if (getStatus() == SimulationStatus.interrupting)
	    setStatus(SimulationStatus.interrupted);
	if (messageBox.isEmpty())
	    setStatus(SimulationStatus.completed);
	else
	    setStatus(SimulationStatus.timeCompleted);
	onFinish();
        System.gc();
    }
}
