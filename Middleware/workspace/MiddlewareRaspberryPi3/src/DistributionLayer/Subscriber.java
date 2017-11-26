package DistributionLayer;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
		
		int sampleSize = 500;
		long [] latency = new long[sampleSize];
		int index = 0;
		
		for(int i = 0; i < sampleSize; i++){
			latency[i] = 0;
		}
		
		while(true){
			msgToBeUnmarshalled = srh.receive();
			msgUnmarshalled = marshaller.unmarshall(msgToBeUnmarshalled);
			long latencyTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-msgUnmarshalled.getHeader().getTime());
			callBack.methodToCallBack(msgUnmarshalled.getBody().getMessage(), msgUnmarshalled.getHeader().getTopic(),latencyTime);

			//			latency[index] = latencyTime;
//			System.out.println(latencyTime);
//			index = index + 1;
//			latencyTime = 0;
//			if(index >= sampleSize){
//				break;
//			}
		}
		
//		double mean = mean(latency);
//		double desviation = desviation(mean, latency);
//		System.out.println("mean = " + mean);
//		System.out.println("stdev = " + desviation);

	}
	
	public static double mean(long[] m){
	    double sum = 0;
	    for (int i = 0; i < m.length; i++) {
	        sum += m[i];
	        System.out.println(i);
	    }
	    return sum /(double)m.length;
	}
	
	public static double desviation(double mean, long[] m ){
		
	    double sum = 0;
	    double stdev = 0;
	    for (int i = 0; i < m.length; i++) {
	        sum = (double) (sum + Math.pow(Math.abs(m[i] - mean),2));   
	        System.out.println(i);
	    }
		stdev = (double) Math.sqrt(sum);
		stdev = stdev/(double)m.length;
		return stdev;
	}
	

	public int getPortListener() {
		return portListener;
	}

	public void setPortListener(int portListener) {
		this.portListener = portListener;
	}
}
