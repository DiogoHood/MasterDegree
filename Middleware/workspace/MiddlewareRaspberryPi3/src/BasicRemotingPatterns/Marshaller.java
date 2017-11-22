package BasicRemotingPatterns;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Message.Message;

public class Marshaller {
	
	public Marshaller(){
		
//		System.out.println("@Marshaller@Marshaller@ Instantiating a Marshaller...");
		
	}
	
	public byte[] marshall(Message msgToBeMarshalled) throws IOException, InterruptedException{
		
//		System.out.println("@Marshaller@marshall@ Marshalling a Message...");
		ByteArrayOutputStream  byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		objectStream.writeObject(msgToBeMarshalled);
		
		return byteStream.toByteArray();		
	}
	
	public Message unmarshall(byte[] msgToBeUnmarshalled) throws IOException, InterruptedException, ClassNotFoundException{
		
//		System.out.println("@Marshaller@unmarshall@ Unmarshalling a Message...");
		ByteArrayInputStream byteStream = new ByteArrayInputStream(msgToBeUnmarshalled);
		ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		
		return (Message) objectStream.readObject();	
	}
}
