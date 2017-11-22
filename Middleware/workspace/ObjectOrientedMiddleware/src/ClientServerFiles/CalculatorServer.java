package ClientServerFiles;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import commonservices.naming.NamingProxy;

import BasicRemotingPatterns.Invoker;
import BasicRemotingPatterns.ObjectsPull;

public class CalculatorServer {

	public static void main(String[] args) throws IOException, Throwable {
		
		AtomicInteger ID_GENERATOR = new AtomicInteger(0);
		
		System.out.println("CalculatorServer@main@ Starting Calculator Server...");
		
		// obtain instance of Invoker
		Invoker invoker = new Invoker("localhost",8013, ID_GENERATOR.getAndIncrement());
		
		// obtain instance of Naming Service
		NamingProxy namingService = new NamingProxy("localhost",1313);
		
		// obtain instance of ObjectsPull
		ObjectsPull objectsPull = new ObjectsPull();
		
		// remote object
		CalculatorProxy calculator = new CalculatorProxy("localhost",8013,invoker.getInvokerID(),ID_GENERATOR.getAndIncrement());
		CalculatorProxy calculato2 = new CalculatorProxy("localhost",8013,invoker.getInvokerID(),ID_GENERATOR.getAndIncrement());
		CalculatorProxy calculato3 = new CalculatorProxy("localhost",8013,invoker.getInvokerID(),ID_GENERATOR.getAndIncrement());
		
		// add objects in the objects pull
		objectsPull.addObject(calculator);
		objectsPull.addObject(calculato2);
		objectsPull.addObject(calculato3);
		
		// register calculator in Naming service
		namingService.bind("Calculator", calculator);

		// invoke Invoker
		invoker.invoke(objectsPull);
	}
}
