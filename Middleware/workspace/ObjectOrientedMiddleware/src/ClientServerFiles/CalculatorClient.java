package ClientServerFiles;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.portable.UnknownException;

import commonservices.naming.NamingProxy;


public class CalculatorClient {

	public static void main(String[] args) throws UnknownException, IOException, Throwable {
		
		int sampleSize = 5000;
		double[] timesBetweenInvocation = getTimesBetweenInvoctions(sampleSize);
		long[] invocationTimes = new long[sampleSize];
		
//		 create an instance of Naming Service
		NamingProxy namingService = new NamingProxy("localhost", 1313);
		
//		 look for Calculator in Naming service
		CalculatorProxy calculatorProxy = (CalculatorProxy) namingService.lookup("Calculator");
		
		//invoke the remote operation
		float result = 0;
		long t1, t2;
					
		for(int i = 1; i < sampleSize; i++){
			
			Thread.sleep((long) timesBetweenInvocation[i-1]);
			// invoke calculator
			t1 = System.nanoTime();
			result = calculatorProxy.mul(i,i);
			t2 = System.nanoTime();
			
			invocationTimes[i-1] = TimeUnit.NANOSECONDS.toMillis(t2 - t1);
		}
		
		saveResult(invocationTimes);
	}
	
	public static double[] getTimesBetweenInvoctions(int sampleSize) {
		
		double desiredStandardDeviation = 0.01;
		double desiredMean = 10.0;
		double[] times = new double[sampleSize];
		Random r = new Random();
		
		for(int i = 0; i < sampleSize; i++){
			times[i] = r.nextGaussian()*desiredStandardDeviation+desiredMean;
		}
		
		return times;	
	}
	
	public static void saveResult(long[] invocationTimes){
		
		long totalTime = 0;
		for(int i = 0; i < invocationTimes.length; i++){
			totalTime = totalTime + invocationTimes[i];
		}
		System.out.println("Tempo total = " + totalTime);
	}
}
