package middlewareRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class CalculatorClientRMI {

	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException{
		
		int sampleSize = 7500;
		double[] timesBetweenInvocation = getTimesBetweenInvoctions(sampleSize);
		long[] invocationTimes = new long[sampleSize];
		
		// obtain a reference to a bootstrap remote object registry
		Registry registry = LocateRegistry.getRegistry("localhost", 1313);
		
		//look for an instance of Calculator in the Naming service
		ICalculatorRMI calculator = (ICalculatorRMI) registry.lookup("Calculator");
		
		//invoke the remote operation
		float result = 0;
		long t1, t2;
					
		for(int i = 1; i < sampleSize; i++){
			
			Thread.sleep((long) timesBetweenInvocation[i-1]);
			// invoke calculator
			t1 = System.nanoTime();
			result = calculator.mul(i,i);
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
