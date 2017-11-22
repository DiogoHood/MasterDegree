package ClientServerFiles;

import java.io.Serializable;
import java.util.ArrayList;

public class Invocation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int objectId;
	private String ipAddress;
	private String operationName;
	private int portNumber;
	ArrayList<Object> parameters = new ArrayList<Object>();
	
	public Invocation(){
		
	}
	
	public void setObjectId(int objectId){
		this.objectId = objectId;
	}
	
	public int getObjectId(){
		return this.objectId;
	}
	
	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}
	
	public String getIpAddress(){
		return this.ipAddress;
	}
	
	public void setOperationName(String operationName){
		this.operationName = operationName;
	}
	
	public String getOperationName(){
		return this.operationName;
	}
	
	public void setPortNumber(int portNumber){
		this.portNumber = portNumber;
	}
	
	public int getPortNumber(){
		return this.portNumber;
	}
	
	public void setParameters(ArrayList<Object> parameters){
		this.parameters = parameters;
	}
	
	public ArrayList<Object> getParameters(){
		return this.parameters;
	}
}
