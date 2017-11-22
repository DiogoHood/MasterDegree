package Message;

import java.io.Serializable;

public class RequestHeader implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String context;
	private int requestId;
	private boolean responseExpected;
	private int objectKey;
	private String operation;
	
	public RequestHeader(String context, int requestId, boolean responseExpected, int objectKey, String operation){
		
		this.context = context;
		this.requestId = requestId;
		this.responseExpected = responseExpected;
		this.objectKey = objectKey;
		this.operation = operation;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	
	public String getContext(){
		return this.context;
	}
	
	public void setRequestId(int requestId){
		this.requestId = requestId;
	}
	
	public int getRequestId(){
		return this.requestId;
	}	
	
	public void setResponseExpected(boolean responseExpected){
		this.responseExpected = responseExpected;
	}
	
	public boolean getResponseExpected(){
		return this.responseExpected;
	}
	
	public void setObjectKey(int objectKey){
		this.objectKey = objectKey;
	}
	
	public int getObjectKey(){
		return this.objectKey;
	}
	public void setOperation(String operation){
		this.operation = operation;
	}
	
	public String getOperation(){
		return this.operation;
	}
}
