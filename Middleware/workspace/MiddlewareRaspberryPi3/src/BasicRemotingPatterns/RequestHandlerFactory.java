package BasicRemotingPatterns;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RequestHandlerFactory {
	
	public static IRequestHandler getRequestHandler(String host, int portNumber) throws IOException{
		
//		System.out.println("@RequestHandlerFactory@getRequestHandler@ Checking the type of communication...");
//		Properties prop = getProp();
//		String comunicationType = prop.getProperty("prop.comunication.type");
		String comunicationType = "TCP";
//		System.out.println(comunicationType);
		
		if(comunicationType.equals("TCP")){
//			System.out.println("@RequestHandlerFactory@getRequestHandler@ Starting a ClientRequestHandlerTCP...");
			return new ClientRequestHandlerTCP(host, portNumber);
		}
		else if(comunicationType.equals("UDP")){
//			System.out.println("@RequestHandlerFactory@getRequestHandler@ Starting a ClientRequestHandlerUDP...");
			return new ClientRequestHandlerUDP(host, portNumber);
		}
		
		return null;
	}
	
	public static IRequestHandler getRequestHandler(int portNumber) throws IOException{
		
//		System.out.println("@RequestHandlerFactory@getRequestHandler@ Checking the type of communication...");
//		Properties prop = getProp();
//		String comunicationType = prop.getProperty("prop.comunication.type");
		String comunicationType = "TCP";
//		System.out.println(comunicationType);
		
		if(comunicationType.equals("TCP")){
//			System.out.println("@RequestHandlerFactory@getRequestHandler@ Starting a ServerRequestHandlerTCP...");
			return new ServerRequestHandlerTCP(portNumber);
		}
		else if(comunicationType.equals("UDP")){
//			System.out.println("@RequestHandlerFactory@getRequestHandler@ Starting a ServerRequestHandlerUDP...");
			return new ServerRequestHandlerUDP(portNumber);
		}
		
		return null;
	}
	
	public static Properties getProp() throws IOException{
		
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/dados.properties");
		props.load(file);
		return props;
		
	}
}
