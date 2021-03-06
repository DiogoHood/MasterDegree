package DistributionLayer;

public class NodeHandleImp implements INodeHandler{
	
	private String senderIP;
	private int senderPort;
	private long time;

	@Override
	public void advertise(String topic) throws Throwable {
		QueueManager.create(topic);
	}

	@Override
	public void publish(String topic, String message)
			throws Throwable {
		QueueManager.insert(topic, message, this.time);
	}

	@Override
	public void subscribe(String topic) throws Throwable {
		SubscribersManager.include(topic, this.getSenderIP(), this.getSenderPort());
	}

	@Override
	public void unsubscribe(String topic) throws Throwable {
		SubscribersManager.remove(topic, this.getSenderIP());
	}

	public String getSenderIP() {
		return senderIP;
	}

	public void setSenderIP(String senderIP) {
		this.senderIP = senderIP;
	}

	public int getSenderPort() {
		return senderPort;
	}

	public void setSenderPort(int senderPort) {
		this.senderPort = senderPort;
	}
	
	
	public long getTime(){
		return this.time;
	}
	
	public void setTime(long time){
		this.time = time;
	}
}
