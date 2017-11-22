package BasicRemotingPatterns;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerRequestHandlerUDP implements IRequestHandler{
	
private int portNumber;
	
	/*UDP comunication*/
	private DatagramSocket serverSocket = null;
	private DatagramPacket receivePacket = null;
	
	private InetAddress IPAddress = null;
	private DatagramPacket sendPacket = null;
	
	public ServerRequestHandlerUDP (int port) {
		
		this.portNumber = port;
		
//		System.out.println("@ServerRequestHandlerUDP@ServerRequestHandlerUDP@ Starting UDP server...");
		
		try{
			serverSocket = new DatagramSocket(portNumber);
		} catch (Exception e){
			System.out.println("Erro@ServerRequestHandlerUDP@ServerRequestHandlerUDP: " + e.getMessage());
		}
	}
	
	public byte [] receive(){
		
//		System.out.println("@ServerRequestHandlerUDP@ServerRequestHandlerUDP@receive@ Receiving a Message...");
		byte[] receiveData = null;
		
		try {				
		
			receiveData = new byte[1024];
			receivePacket = new DatagramPacket(receiveData,receiveData.length);
			serverSocket.receive(receivePacket);
			IPAddress = receivePacket.getAddress();
			portNumber = receivePacket.getPort();
			receiveData = receivePacket.getData();				

		}catch (Exception e){
			System.out.println("@Erro@ServerRequestHandlerUDP@receive: " + e.getMessage());
		}
		
		return receiveData;		
	}	

	public void send(byte [] msg){
		
		byte[]  sendData = new byte[1024];
		
//		System.out.println("@ServerRequestHandlerUDP@ServerRequestHandlerUDP@send@ Seding a Message...");
		
		try{
			sendData = msg;
			sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, portNumber);
			serverSocket.send(sendPacket);	
	
		} catch (Exception e){
			System.out.println("Erro@ClientRequestHandlerUDP@send: " + e.getMessage());
		}

	}

}
