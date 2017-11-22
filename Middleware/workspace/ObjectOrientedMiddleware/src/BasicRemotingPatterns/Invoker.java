package BasicRemotingPatterns;

import java.io.IOException;

import ClientServerFiles.CalculatorImpl;
import ClientServerFiles.Termination;
import Message.Message;
import Message.MessageBody;
import Message.MessageHeader;
import Message.ReplyBody;
import Message.ReplyHeader;

public class Invoker {
	
	private String host;
	private int port;
	private int invokerID;
	
	public Invoker(String host, int port, int invokerID){
		this.host = host;
		this.port = port;
		this.invokerID = invokerID;
	}
	
	public String getHost(){
		return this.host;
	}
	
	public int getPort(){
		return this.port;
	}
	
	public int getInvokerID(){
		return this.invokerID;
	}

	public void invoke(ObjectsPull objectsPull) throws IOException, Throwable {
		
		System.out.println("@Invoker@invoke@ Calling invoke...");
		IRequestHandler srh = RequestHandlerFactory.getRequestHandler(this.getPort());
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = new Message(null,null);
		Marshaller mrsh = new Marshaller();
		Termination ter = new Termination();
		
		// create remote object
		CalculatorImpl rObj = new CalculatorImpl();
		
		while(true){
			
			// @ Receive Message
			msgToBeUnmarshalled = srh.receive();
			
			// @ Unmarshall received message
			msgUnmarshalled = mrsh.unmarshall(msgToBeUnmarshalled);
			
			if(objectsPull.getObject(msgUnmarshalled.getBody().getRequestHeader().getObjectKey()) != null){
				
				switch(msgUnmarshalled.getBody().getRequestHeader().getOperation()){
				
				case "add":
					// @ Invokes the remote object
	//				System.out.println("@Invoker@invoke@ Executing a add operation...");
					Float _add_p1 = (Float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					Float _add_p2 = (Float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
					ter.setOperationResult(rObj.add(_add_p1, _add_p2));
					
					Message _add_msgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getOperationResult())));
					
					// @ Marshall the reponse
					msgMarshalled = mrsh.marshall(_add_msgToBeMarshalled);
					
					// @ Send response
					srh.send(msgMarshalled);
					break;
					
				case "sub":
					// @ Invokes the remote object
	//				System.out.println("@Invoker@invoke@ Executing a add operation...");
					Float _sub_p1 = (Float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					Float _sub_p2 = (Float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
					ter.setOperationResult(rObj.sub(_sub_p1, _sub_p2));
					
					Message _sub_msgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getOperationResult())));
					
					// @ Marshall the reponse
					msgMarshalled = mrsh.marshall(_sub_msgToBeMarshalled);
					
					// @ Send response
					srh.send(msgMarshalled);
					break;
					
				case "div":
					// @ Invokes the remote object
	//				System.out.println("@Invoker@invoke@ Executing a add operation...");
					Float _div_p1 = (Float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					Float _div_p2 = (Float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
					ter.setOperationResult(rObj.div(_div_p1, _div_p2));
					
					Message _div_msgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getOperationResult())));
					
					// @ Marshall the reponse
					msgMarshalled = mrsh.marshall(_div_msgToBeMarshalled);
					
					// @ Send response
					srh.send(msgMarshalled);
					break;
					
				case "mul":
					// @ Invokes the remote object
	//				System.out.println("@Invoker@invoke@ Executing a add operation...");
					Float _mul_p1 = (Float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					Float _mul_p2 = (Float) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
					ter.setOperationResult(rObj.mul(_mul_p1, _mul_p2));
					
					Message _mul_msgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getOperationResult())));
					
					// @ Marshall the reponse
					msgMarshalled = mrsh.marshall(_mul_msgToBeMarshalled);
					
					// @ Send response
					srh.send(msgMarshalled);
					break;
				}
			}
			else{
				System.out.println("Erro@invoke@ Invalid Object!");
			}
		}		
	}
}

