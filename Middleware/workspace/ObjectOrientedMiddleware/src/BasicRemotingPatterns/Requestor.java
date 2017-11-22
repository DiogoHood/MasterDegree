package BasicRemotingPatterns;

import java.io.IOException;
import java.net.UnknownHostException;

import ClientServerFiles.Invocation;
import ClientServerFiles.Termination;
import Message.Message;
import Message.MessageBody;
import Message.MessageHeader;
import Message.RequestBody;
import Message.RequestHeader;

public class Requestor {
	
	protected String host;
	protected int port;
	
	public Requestor(String host, int port){
		
		this.host = host;
		this.port = port;
		
	}
	
	public String getHost(){
		return this.host;
	}
	
	public int getPort(){
		return this.port;
	}
	
	public Termination invoke(Invocation inv) throws UnknownHostException, IOException, Throwable{
		
		IRequestHandler crh = RequestHandlerFactory.getRequestHandler(getHost(),getPort());
		Marshaller marshaller = new Marshaller();
		Termination termination = new Termination();
		
		byte [] msgMarshalled;
		byte [] msgToBeUnmarshalled;
		
		Message msgUnmarshalled = new Message(null,null);
		
		// map Invocation into a Message
		RequestHeader requestHeader = new RequestHeader("",0,true,inv.getObjectId(),inv.getOperationName());
		RequestBody requestBody = new RequestBody(inv.getParameters());
		MessageHeader messageHeader = new MessageHeader("MIOP",0, false, 0, 0);
		MessageBody messageBody = new MessageBody(requestHeader,requestBody, null, null);
		Message msgToBeMarshalled = new Message(messageHeader,messageBody);
		
		// marshall request message
		msgMarshalled = marshaller.marshall(msgToBeMarshalled);
		
		// send marshalled message
		crh.send(msgMarshalled);
		
		// receive reply message
		msgToBeUnmarshalled = crh.receive();
		
		// unmarshall reply message
		msgUnmarshalled = marshaller.unmarshall(msgToBeUnmarshalled);
		
		//return result to Client Proxy
		termination.setOperationResult((Object) msgUnmarshalled.getBody().getReplyBody().getOperationResult());
		
		return termination;
	}
}
