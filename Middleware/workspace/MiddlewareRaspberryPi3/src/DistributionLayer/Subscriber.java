package DistributionLayer;
import java.io.IOException;

import BasicRemotingPatterns.IRequestHandler;
import BasicRemotingPatterns.Marshaller;
import BasicRemotingPatterns.RequestHandlerFactory;
import Message.Message;

public class Subscriber extends NodeHandle{
	
	private int portListener;
	
	public Subscriber(String host, int portNodeMaster) {
		super(host, portNodeMaster);
	}

	public void receive(CallBack callBack) throws IOException, InterruptedException, ClassNotFoundException{
		
		IRequestHandler srh = RequestHandlerFactory.getRequestHandler(this.getPortListener());
		Marshaller marshaller = new Marshaller();
		byte[] msgToBeUnmarshalled = null;
		Message msgUnmarshalled = null;
		
		int sampleSize = 1000;
		int index = 0;
		
		while(true){
			msgToBeUnmarshalled = srh.receive();
			msgUnmarshalled = marshaller.unmarshall(msgToBeUnmarshalled);
			callBack.methodToCallBack(msgUnmarshalled.getBody().getMessage(), msgUnmarshalled.getHeader().getTopic(),System.nanoTime()-msgUnmarshalled.getHeader().getTime());
			index = index + 1;
			if(index >= sampleSize){
				break;
			}
		}
	}

	public int getPortListener() {
		return portListener;
	}

	public void setPortListener(int portListener) {
		this.portListener = portListener;
	}
}
