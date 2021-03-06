package DistributionLayer;

public interface INodeHandler {
	
	public void advertise(String topic)throws Throwable;
	public void publish(String topic, String message) throws Throwable;
	public void subscribe(String topic) throws Throwable;
	public void unsubscribe(String topic) throws Throwable;

}
