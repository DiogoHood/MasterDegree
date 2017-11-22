package BasicRemotingPatterns;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRequestHandlerUDP implements IRequestHandler {
	
	private String host;
	private int portNumber;
	
	private DatagramSocket clientSocketUDP = null;
	
	private DatagramPacket sendPacket = null;
	private DatagramPacket receivePacket = null;
	
	
	public ClientRequestHandlerUDP(String server, int portNumber) throws IOException{
		
		this.host = server;
		this.portNumber = portNumber;
	}
	
	public void send(byte [] msg){
		
//		System.out.println("ClientRequestHandlerUDP@send@ Sending a Messase...");
		
		try{
			byte[] sendData = new byte[1024];
			sendData = msg;
			InetAddress IPAddress = InetAddress.getByName(host);
			clientSocketUDP = new DatagramSocket();
			sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, portNumber);
//			System.out.println("Enviando pacote UDP para " + server + ":" + portNumber);
			clientSocketUDP.send(sendPacket);
			
		} catch (IOException e) {
			System.out.println("Erro@ClientRequestHandlerUDP@send: " + e.getMessage());
		}
	}
	
	public byte [] receive (){
		
		byte[] receiveData = null;
		
//		System.out.println("ClientRequestHandlerUDP@send@ Receiving a Messase...");
		
		try{
			receiveData = new byte[1024];
			receivePacket = new DatagramPacket(receiveData,receiveData.length);
			clientSocketUDP.receive(receivePacket);
			receiveData = receivePacket.getData();
			clientSocketUDP.close();

			
		} catch(IOException e){
			System.out.println("Erro@ClientRequestHandlerUDP@receive: " + e.getMessage());
		}
		
		return receiveData;
	}

}
