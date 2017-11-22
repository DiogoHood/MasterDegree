package BasicRemotingPatterns;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandlerTCP implements IRequestHandler {
	
	private int portNumber;
	
	/*TCP comunication*/
	private ServerSocket welcomeSocket = null;
	private Socket connectionSocket = null;
	
	private int sentMessageSize;
	private int receivedMessageSize;
	private DataOutputStream outToClient = null;
	private DataInputStream inFromClient = null;
	
	public ServerRequestHandlerTCP(int port) {
		
		this.portNumber = port;
		
//		System.out.println("@ServerRequestHandlerTCP@ServerRequestHandlerTCP@ Starting TCP server...");
		
		try{
			welcomeSocket = new ServerSocket(portNumber);
			
		} catch (Exception e){
			System.out.println("Erro@ServerRequestHandlerTCP@ServerRequestHandlerTCP@ " + e.getMessage() );
		}
	}
		
	public byte [] receive(){
		
		byte[] receiveData = null;
		
//		System.out.println("@ServerRequestHandlerTCP@receive@ Waiting to receive a Messase...");
		
		try {
		
			while(true){
				
				connectionSocket = welcomeSocket.accept();
				System.out.println("ServerRequestHandlerTCP@receive@ Message received!");
//				System.out.println("Client conected: " + connectionSocket.getInetAddress().getHostAddress());
				inFromClient = new DataInputStream(connectionSocket.getInputStream());
				receivedMessageSize = inFromClient.readInt();
				receiveData = new byte[receivedMessageSize];
				inFromClient.read(receiveData);
				return receiveData;
			}

				
		}catch (Exception e){
			System.out.println("Erro@ServerRequestHandlerTCP@receive: " + e.getMessage());
		}
		
		return receiveData;		
	}	

	public void send(byte [] msg){
		
//		System.out.println("@ServerRequestHandlerTCP@receive@ Sending a message...");
		
		try{

			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			sentMessageSize = msg.length;
			outToClient.flush();
			outToClient.writeInt(sentMessageSize);
			outToClient.write(msg);

		} catch (Exception e){
			System.out.println("Erro@ServerRequestHandlerTCP@send: " + e.getMessage());
		}
	}
}
