package commonservices.naming;

import java.io.IOException;

import BasicRemotingPatterns.IRequestHandler;
import BasicRemotingPatterns.Marshaller;
import BasicRemotingPatterns.RequestHandlerFactory;
import ClientServerFiles.CalculatorProxy;
import ClientServerFiles.Termination;
import Message.Message;
import Message.MessageBody;
import Message.MessageHeader;
import Message.ReplyBody;
import Message.ReplyHeader;

public class NamingInvoker {

	public void invoke(NamingProxy namingProxy) throws IOException, Throwable {
		
//		System.out.println("NamingInvoker@invoke@ Calling invoke...");
		IRequestHandler srh = RequestHandlerFactory.getRequestHandler(namingProxy.getPort());
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = new Message(null,null);
		Marshaller mrsh = new Marshaller();
		Termination ter = new Termination();
		
		// create remote object
		NamingImpl rObj = new NamingImpl(namingProxy.getHost(),namingProxy.getPort());
		
		while(true){
			
			// @ Receive Message
			msgToBeUnmarshalled = srh.receive();
			
			// @ Unmarshall received message
			msgUnmarshalled = mrsh.unmarshall(msgToBeUnmarshalled);
			
			switch(msgUnmarshalled.getBody().getRequestHeader().getOperation()){
			
			case "bind":
				
				// @ Invokes the remote object
//				System.out.println("NamingInvoker@invoke@ Executing a bind operation...");
				String serviceName = (String) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
				CalculatorProxy clientProxy = (CalculatorProxy) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
				
				// Executing the bind operation
				rObj.bind(serviceName, clientProxy);
				
				// Executing the lookup operation
				ter.setOperationResult("ok");	
				
				Message _bind_msgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getOperationResult())));
				
				// @ Marshall the reponse
				msgMarshalled = mrsh.marshall(_bind_msgToBeMarshalled);
				
				// @ Send response
				srh.send(msgMarshalled);				

				break;
				
			case "lookup":
				
				// @ Invokes the remote object
//				System.out.println("NamingInvoker@invoke@ Executing a lookup operation...");
				String serviceName1 = (String) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);

				// Executing the lookup operation
				ter.setOperationResult(rObj.lookup(serviceName1));	
				
				Message _lookup_msgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, false, 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getOperationResult())));
				
				// @ Marshall the reponse
				msgMarshalled = mrsh.marshall(_lookup_msgToBeMarshalled);
				
				// @ Send response
				srh.send(msgMarshalled);
				
				break;
				
			case "list":
				
				// @ Invokes the remote object
//				System.out.println("NamingInvoker@invoke@ Executing a list operation...");
				
				// Executing the list operation
				ter.setOperationResult(rObj.list());	
				
				Message _add_msgToBeMarshalled1 = new Message(new MessageHeader("protocolo", 0, false, 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getOperationResult())));
				
				// @ Marshall the reponse
				msgMarshalled = mrsh.marshall(_add_msgToBeMarshalled1);
				
				// @ Send response
				srh.send(msgMarshalled);
				break;
			}
		}
	}
}
