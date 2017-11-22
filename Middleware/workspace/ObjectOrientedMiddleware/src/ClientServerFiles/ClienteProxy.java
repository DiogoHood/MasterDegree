package ClientServerFiles;

import java.io.Serializable;

public class ClienteProxy implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected String host;
	protected int port;
	protected int objectId;
	
	public String getHost(){
		
		return this.host;
	}
	
	public void setHost(String host){
		
		this.host = host;
	}
	
	public int getPort(){
		
		return this.port;
	}
	
	public void setPort(int port){
		
		this.port = port;
	}
	
	public int getObjectId(){
		
		return this.objectId; 
	}
	
	public void setObjectId(int objectId){
		
		this.objectId = objectId;
	}

}
