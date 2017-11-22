package commonservices.naming;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.omg.CORBA.portable.UnknownException;

import BasicRemotingPatterns.Requestor;
import ClientServerFiles.CalculatorProxy;
import ClientServerFiles.Invocation;
import ClientServerFiles.Termination;

public class NamingProxy implements INaming{
	
	private String host;
	private int port;
	protected NamingRepository namingRepository = new NamingRepository();
	
	public NamingProxy(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	public void bind(String serviceName, CalculatorProxy calculatorProxy) throws UnknownException, IOException, Throwable{
		
		Invocation inv = new Invocation();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{};
		String methodName = null;
		Requestor requestor = new Requestor(getHost(),getPort());
		
		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(serviceName);
		parameters.add(calculatorProxy);
		
		// information sent to Requestor
		inv.setObjectId(calculatorProxy.getAOR().getObjectID());
		inv.setIpAddress(calculatorProxy.getAOR().getHost());
		inv.setPortNumber(calculatorProxy.getAOR().getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
				
		// invoke Requestor
		requestor.invoke(inv);

	}
	
	public CalculatorProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable{
		
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{};
		String methodName = null;
		Requestor requestor = new Requestor(getHost(),getPort());
		
		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(serviceName);
		
		// information sent to Requestor
		inv.setIpAddress(this.getHost());
		inv.setPortNumber(this.getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		// invoke Requestor
		ter = requestor.invoke(inv);
		
		// @Result sent back to Client
		return (CalculatorProxy) ter.getOperationResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> list() throws UnknownHostException, IOException, Throwable{
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		class Local{};
		String methodName = null;
		Requestor requestor = new Requestor(getHost(),getPort());
		
		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();
		
		// information sent to Requestor
		inv.setIpAddress(this.getHost());
		inv.setPortNumber(this.getPort());
		inv.setOperationName(methodName);
		
		// invoke Requestor
		ter = requestor.invoke(inv);
		
		// @Result sent back to Client
		return (ArrayList<String>) ter.getOperationResult();
	}
	
	public void setHost(String host){
		this.host = host;
	}
	
	public String getHost(){
		return this.host;
	}
	
	public void setPort(int port){
		this.port = port;
	}
	
	public int getPort(){
		return this.port;
	}
	
	public void setNamingRepository(NamingRepository namingRepository){
		this.namingRepository = namingRepository;
	}
	
	public NamingRepository getNamingRepository(){
		return this.namingRepository;
	}

}
