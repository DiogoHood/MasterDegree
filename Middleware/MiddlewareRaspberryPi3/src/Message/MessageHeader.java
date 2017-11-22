package Message;

import java.io.Serializable;

public class MessageHeader implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String senderIP;
	private int senderPort;
	private String method;
	private String topic;
	
	public MessageHeader(String senderIP, int senderPort, String method, String topic){
		this.senderIP = senderIP;
		this.senderPort = senderPort;
		this.method = method;
		this.topic = topic;
	}
	
	public String getSenderIP() {
		return senderIP;
	}
	public void setSenderIP(String senderIP) {
		this.senderIP = senderIP;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getSenderPort() {
		return senderPort;
	}
	public void setSenderPort(int senderPort) {
		this.senderPort = senderPort;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
}
