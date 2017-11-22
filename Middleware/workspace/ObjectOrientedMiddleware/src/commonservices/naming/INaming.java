package commonservices.naming;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.omg.CORBA.portable.UnknownException;

import ClientServerFiles.CalculatorProxy;


public interface INaming{
	
	public void bind(String serviceName, CalculatorProxy clientProxy) throws UnknownException, IOException, Throwable;
	
	public CalculatorProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable;
	
	public ArrayList<String> list() throws UnknownHostException, IOException, Throwable;
}
