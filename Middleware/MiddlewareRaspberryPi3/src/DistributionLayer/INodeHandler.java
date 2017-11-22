package DistributionLayer;

public interface INodeHandler {
	
	public void advertise(String topic, String sourceIP)throws Throwable;
	public void publish(String topic, String message, String sourceIP) throws Throwable;
	public void subscribe(String topic, String host, int port) throws Throwable;
	public void unsubscribe(String topic, String host) throws Throwable;
	public String list(String sourceIP) throws Throwable;

}
