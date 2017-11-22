package Message;

import java.io.Serializable;

public class MessageBody implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public MessageBody(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
