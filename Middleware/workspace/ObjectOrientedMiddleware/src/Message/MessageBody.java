package Message;

import java.io.Serializable;

public class MessageBody implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private RequestHeader requestHeader;
	private RequestBody requestBody;
	private ReplyHeader replyHeader;
	private ReplyBody replyBody;

	public MessageBody(RequestHeader requestHeader, RequestBody requestBody, ReplyHeader replyHeader, ReplyBody replyBody) {
		this.requestHeader = requestHeader;
		this.requestBody = requestBody;
		this.replyHeader = replyHeader;
		this.replyBody = replyBody;
	}
	
	public void setRequestHeader(RequestHeader requestHeader){
		this.requestHeader = requestHeader;
	}
	
	public RequestHeader getRequestHeader(){
		return this.requestHeader;
	}
	
	public void setRequestBody(RequestBody requestBody){
		this.requestBody = requestBody;
	}
	
	public RequestBody getRequestBody(){
		return this.requestBody;
	}
	
	public void setReplyHeader(ReplyHeader replyHeader){
		this.replyHeader = replyHeader;
	}
	
	public ReplyHeader getReplyHeader(){
		return this.replyHeader;
	}
	
	public void setReplyBody(ReplyBody replyBody){
		this.replyBody = replyBody;
	}
	
	public ReplyBody getReplyBody(){
		return this.replyBody;
	}	
}
