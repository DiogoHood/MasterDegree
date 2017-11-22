package DistributionLayer;

import Message.Message;

public class Demux implements Runnable{

	private Message message;
	private NodeHandleImp nodeHandle = new NodeHandleImp();
	
	@Override
	public void run() {
		
		switch(message.getHeader().getMethod()){
		case "advertise":				
			try {
				nodeHandle.advertise(message.getHeader().getTopic());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case "publish":				
			try {
				nodeHandle.publish(message.getHeader().getTopic(), message.getBody().getMessage());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "subscribe":
			try {
				nodeHandle.setSenderIP(message.getHeader().getSenderIP());
				nodeHandle.setSenderPort(message.getHeader().getSenderPort());				
				nodeHandle.subscribe(message.getHeader().getTopic());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "unsubscribe":
			try {
				nodeHandle.setSenderIP(message.getHeader().getSenderIP());
				nodeHandle.setSenderPort(message.getHeader().getSenderPort());	
				nodeHandle.unsubscribe(message.getHeader().getTopic());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:
			System.out.println("EventService@start@ Operation is not implemented in the Server");
			break;
		}		
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
}
