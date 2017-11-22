package Message;
import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private MessageHeader header;
	private MessageBody body;
	
	public Message(MessageHeader header,MessageBody body) {
		
//		System.out.println("Message@Message@ Instantiating a Message...");
		this.header = header;
		this.body = body;
	}
	
	public void setHeader(MessageHeader body){
		this.header = body;
	}
	
	public MessageHeader getHeader(){
		return this.header;
	}
	
	public void setBody(MessageBody body){
		this.body = body;
	}
	
	public MessageBody getBody(){
		return this.body;
	}
	
	
}
