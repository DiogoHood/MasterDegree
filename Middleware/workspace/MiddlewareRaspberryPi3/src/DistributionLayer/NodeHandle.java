package DistributionLayer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import BasicRemotingPatterns.IRequestHandler;
import BasicRemotingPatterns.Marshaller;
import BasicRemotingPatterns.RequestHandlerFactory;
import Message.Message;
import Message.MessageBody;
import Message.MessageHeader;

public class NodeHandle implements INodeHandler {
	
	private String host;
	private int port;
	
	public NodeHandle(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void advertise(String topic, String sourceIP) throws Throwable {
		
		IRequestHandler crh = RequestHandlerFactory.getRequestHandler(this.getHost(),this.getPort());
		Marshaller marshaller = new Marshaller();
		byte[] msgMarshalled = null;		
		Message msgToBeMarshalled = new Message(new MessageHeader(null, 0, null, null), new MessageBody(null));
		
		// information received from Client
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		msgToBeMarshalled.getHeader().setMethod(methodName);
		msgToBeMarshalled.getHeader().setTopic(topic);
		msgToBeMarshalled.getHeader().setSenderIP(this.getIPAddress());
		
		try {
			msgMarshalled = marshaller.marshall(msgToBeMarshalled);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crh.send(msgMarshalled);
		
	}

	@Override
	public void publish(String topic, String message, String sourceIP)
			throws Throwable {
		
		IRequestHandler crh = RequestHandlerFactory.getRequestHandler(this.getHost(),this.getPort());
		Marshaller marshaller = new Marshaller();
		byte[] msgMarshalled = null;		
		Message msgToBeMarshalled = new Message(new MessageHeader(null, 0, null, null), new MessageBody(null));
		
		// information received from Client
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		msgToBeMarshalled.getHeader().setMethod(methodName);
		msgToBeMarshalled.getHeader().setTopic(topic);
		msgToBeMarshalled.getBody().setMessage(message);
		msgToBeMarshalled.getHeader().setSenderIP(this.getIPAddress());
		
		try {
			msgMarshalled = marshaller.marshall(msgToBeMarshalled);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crh.send(msgMarshalled);			
	}

	@Override
	public void subscribe(String topic, String host, int portListener) throws Throwable {
		
		IRequestHandler crh = RequestHandlerFactory.getRequestHandler(this.getHost(),this.getPort());
		Marshaller marshaller = new Marshaller();
		byte[] msgMarshalled = null;		
		Message msgToBeMarshalled = new Message(new MessageHeader(null, 0, null, null), new MessageBody(null));
		
		// information received from Client
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		msgToBeMarshalled.getHeader().setMethod(methodName);
		msgToBeMarshalled.getHeader().setTopic(topic);
		msgToBeMarshalled.getHeader().setSenderIP(this.getIPAddress());
		msgToBeMarshalled.getHeader().setSenderPort(portListener);
		
		try {
			msgMarshalled = marshaller.marshall(msgToBeMarshalled);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crh.send(msgMarshalled);		
	}

	@Override
	public void unsubscribe(String topic, String sourceIP) throws Throwable {
		
		IRequestHandler crh = RequestHandlerFactory.getRequestHandler(this.getHost(),this.getPort());
		Marshaller marshaller = new Marshaller();
		byte[] msgMarshalled = null;		
		Message msgToBeMarshalled = new Message(new MessageHeader(null, 0, null, null), new MessageBody(null));
		
		// information received from Client
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		msgToBeMarshalled.getHeader().setMethod(methodName);
		msgToBeMarshalled.getHeader().setTopic(topic);
		msgToBeMarshalled.getHeader().setSenderIP(this.getIPAddress());
		
		try {
			msgMarshalled = marshaller.marshall(msgToBeMarshalled);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crh.send(msgMarshalled);
		
	}

	@Override
	public String list(String sourceIP) throws Throwable {
		
		IRequestHandler crh = RequestHandlerFactory.getRequestHandler(this.getHost(),this.getPort());
		Marshaller marshaller = new Marshaller();
		byte[] msgMarshalled = null;		
		Message msgToBeMarshalled = new Message(new MessageHeader(null, 0, null, null), new MessageBody(null));
		
		// information received from Client
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		msgToBeMarshalled.getHeader().setMethod(methodName);
		msgToBeMarshalled.getHeader().setSenderIP(this.getIPAddress());
		
		try {
			msgMarshalled = marshaller.marshall(msgToBeMarshalled);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crh.send(msgMarshalled);
		byte[] msgs = crh.receive();
		Message topics = marshaller.unmarshall(msgs);
		
		return topics.getBody().getMessage();
		
	}
	
	public String getIPAddress() throws SocketException{
		
		String add = null;		
	    Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
	    
	    for (; n.hasMoreElements();){
	    	
	        NetworkInterface e = n.nextElement();
	        String nameInterface = e.getName();
	        
	        if(nameInterface.toLowerCase().contains("wl")){
	        	
		        Enumeration<InetAddress> a = e.getInetAddresses();
		        for (; a.hasMoreElements();){
		        	
		        	InetAddress addr = a.nextElement();
		            add = addr.getHostAddress().toString();
		            if( add.length() < 17 )
		                return add;
		        }
	        }
	    }
	    
		return add;
	}
	
	public String getHost(){
		return this.host;
	}
	
	public int getPort(){
		return this.port;
	}
}
