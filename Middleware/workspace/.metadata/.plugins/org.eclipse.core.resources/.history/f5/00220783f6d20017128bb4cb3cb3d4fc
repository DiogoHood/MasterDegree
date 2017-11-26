package DistributionLayer;

import BasicRemotingPatterns.IRequestHandler;
import BasicRemotingPatterns.Marshaller;
import BasicRemotingPatterns.RequestHandlerFactory;
import Message.Message;

public class EventService {
	
	private int port;
	
	public EventService (int port){
		this.port = port;
	}
	
	public void start() throws Throwable{
		
//		System.out.println("EventService@start@ Starting the Event Service...");
		IRequestHandler srh = RequestHandlerFactory.getRequestHandler(port);
		
		Marshaller mrsh = new Marshaller();
		Message msgUnmarshalled = new Message(null,null);
		byte[] msgToBeUnmarshalled = null;
		
		// Thread for check the messages of the message queue of topics 
		Notifier notifier = new Notifier();
		Thread threadNotifier = new Thread(notifier);
		threadNotifier.start();
		
		while(true){
//			System.out.println("EventService@start@ Waiting for new message...");
			// @ Receive Message
			msgToBeUnmarshalled = srh.receive();
			
			// @ Unmarshall received message
			msgUnmarshalled = mrsh.unmarshall(msgToBeUnmarshalled);
			
			// Thread for demultiplex the messages recived
			Demux demux = new Demux();
			demux.setMessage(msgUnmarshalled);
			Thread threadDemux = new Thread(demux);
			threadDemux.start();
		}
	}
}
