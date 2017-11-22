package BasicRemotingPatterns;

import java.io.Serializable;

public class AbsoluteObjectReference implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int port;
	private String host;
	private int invokeId;
	private String protocol;
	private int objectId;
	
	public AbsoluteObjectReference(String host, int port, int invokeId, String protocol, int objectId){
		
		this.host = host;
		this.port = port;
		this.invokeId = invokeId;
		this.protocol = protocol;
		this.objectId = objectId;
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
	
	public void setInvokeID(int invokeId){
		this.invokeId = invokeId;		
	}
	
	public int getInvokeID(){
		return this.invokeId;		
	}
	
	public void setProtocol(String protocol){
		this.protocol = protocol;		
	}
	
	public String getProtocol(){
		return this.protocol;		
	}
	
	public void setObjectID(int objectId){
		this.objectId = objectId;		
	}
	
	public int getObjectID(){
		return this.objectId;		
	}
}
