package BasicRemotingPatterns;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRequestHandlerTCP implements IRequestHandler {
	
	private String host;
	private int portNumber;
	private int sentMessageSize;
	private int receivedMessageSize;
	
	private Socket clientSocket = null;
	private DataOutputStream outToServer = null;
	private DataInputStream inFromServer = null;
	

	public ClientRequestHandlerTCP(String server, int portNumber) throws IOException{
			
		this.host = server;
		this.portNumber = portNumber;
	}
	
	public void send(byte [] msg){
		
//		System.out.println("ClientRequestHandlerTCP@send@ Sending a Messase...");
		
		try{
			
			clientSocket = new Socket(host,portNumber);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			sentMessageSize = msg.length;
			outToServer.writeInt(sentMessageSize);
			outToServer.write(msg);
			outToServer.flush();
			
		}catch (IOException e) {
			System.out.println("Erro@ClientRequestHandlerTCP@send: " + e.getMessage());
		}
	}
	
	public byte [] receive(){
		
//		System.out.println("ClientRequestHandlerTCP@send@ Receiving a Messase...");
		
		byte[] receiveData = null;

		try{
			inFromServer = new DataInputStream(clientSocket.getInputStream());
			receivedMessageSize = inFromServer.readInt();
			receiveData = new byte[receivedMessageSize];
			inFromServer.read(receiveData);
		}
		catch (IOException e) {
			System.out.println("Erro@ClientRequestHandlerTCP@receive: " + e.getMessage());
		}
		
		return receiveData;		
	}
}
