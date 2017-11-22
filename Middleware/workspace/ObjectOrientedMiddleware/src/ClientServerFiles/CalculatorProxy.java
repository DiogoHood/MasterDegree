package ClientServerFiles;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import BasicRemotingPatterns.AbsoluteObjectReference;
import BasicRemotingPatterns.Requestor;

public class CalculatorProxy implements ICalculator, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	AbsoluteObjectReference aor;
	
	public CalculatorProxy(String host, int port, int invokeId, int objectId) throws IOException{
		
		Properties prop = getProp();
		String protocol = prop.getProperty("prop.comunication.type");

		this.aor = new AbsoluteObjectReference(host, port, invokeId, protocol, objectId);
	}
	
	public void setAOR(AbsoluteObjectReference aor){
		this.aor = aor;
	}
	
	public AbsoluteObjectReference getAOR(){
		return this.aor;
	}
		
	public float add(float a, float b) throws Throwable{
		
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{};
		String methodName = null;
		Requestor requestor = new Requestor(this.getAOR().getHost(),this.getAOR().getPort());
		
		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		
		// information sent to Requestor
		inv.setObjectId(this.getAOR().getObjectID());
		inv.setIpAddress(this.getAOR().getHost());
		inv.setPortNumber(this.getAOR().getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		// invoke Requestor
		ter = requestor.invoke(inv);
		
		// @Result sent back to Client
		return (Float) ter.getOperationResult();
		
	}
	
	public float sub(float a, float b) throws Throwable{
		
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{};
		String methodName = null;
		Requestor requestor = new Requestor(this.getAOR().getHost(),this.getAOR().getPort());
		
		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		
		// information sent to Requestor
		inv.setObjectId(this.getAOR().getObjectID());
		inv.setIpAddress(this.getAOR().getHost());
		inv.setPortNumber(this.getAOR().getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		// invoke Requestor
		ter = requestor.invoke(inv);
		
		// @Result sent back to Client
		return (Float) ter.getOperationResult();
	}
	
	public float div(float a, float b) throws Throwable{
		
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{};
		String methodName = null;
		Requestor requestor = new Requestor(this.getAOR().getHost(),this.getAOR().getPort());
		
		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		
		// information sent to Requestor
		inv.setObjectId(this.getAOR().getObjectID());
		inv.setIpAddress(this.getAOR().getHost());
		inv.setPortNumber(this.getAOR().getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		// invoke Requestor
		ter = requestor.invoke(inv);
		
		// @Result sent back to Client
		return (Float) ter.getOperationResult();
	}
	
	public float mul(float a, float b) throws Throwable{
		
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{};
		String methodName = null;
		Requestor requestor = new Requestor(this.getAOR().getHost(),this.getAOR().getPort());
		
		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		
		// information sent to Requestor
		inv.setObjectId(this.getAOR().getObjectID());
		inv.setIpAddress(this.getAOR().getHost());
		inv.setPortNumber(this.getAOR().getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		// invoke Requestor
		ter = requestor.invoke(inv);
		
		// @Result sent back to Client
		return (Float) ter.getOperationResult();
	}
	
	public static Properties getProp() throws IOException{
		
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/dados.properties");
		props.load(file);
		return props;
		
	}
}
