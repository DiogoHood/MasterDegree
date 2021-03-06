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
				System.out.println("Erro@EventService@Start@advertise " + e.getMessage() );
			}
			break;
		
		case "publish":				
			try {
				nodeHandle.setTime(message.getHeader().getTime());
				nodeHandle.publish(message.getHeader().getTopic(), message.getBody().getMessage());
			} catch (Throwable e) {
				System.out.println("Erro@EventService@Start@publish " + e.getMessage() );
			}
			break;
			
		case "subscribe":
			try {
				nodeHandle.setSenderIP(message.getHeader().getSenderIP());
				nodeHandle.setSenderPort(message.getHeader().getSenderPort());				
				nodeHandle.subscribe(message.getHeader().getTopic());
			} catch (Throwable e) {
				System.out.println("Erro@EventService@Start@subscribe " + e.getMessage() );
			}
			break;
			
		case "unsubscribe":
			try {
				nodeHandle.setSenderIP(message.getHeader().getSenderIP());
				nodeHandle.setSenderPort(message.getHeader().getSenderPort());	
				nodeHandle.unsubscribe(message.getHeader().getTopic());
			} catch (Throwable e) {
				System.out.println("Erro@EventService@Start@unsubscribe " + e.getMessage() );
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
