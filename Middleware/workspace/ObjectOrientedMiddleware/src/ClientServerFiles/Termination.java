package ClientServerFiles;

import java.io.Serializable;

public class Termination implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Object operationResult;
	
	public Termination(){
//		System.out.println("Termination@Termination@ Instantiating a Termination...");
		
	}
	
	public void setOperationResult(Object operationResult){
//		System.out.println("Termination@setOperationResult@ Setting the operation result...");
		this.operationResult = operationResult;
	}
	
	public Object getOperationResult(){
		return this.operationResult;
	}
}
