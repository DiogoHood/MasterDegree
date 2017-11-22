package middlewareRMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServerRMI {

	protected CalculatorServerRMI() throws RemoteException{
		super();
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException{
		
		// create an instance of Calculator
		CalculatorImplRMI calculator = new CalculatorImplRMI();
		
		// create a Registry instance on the local host
		Registry registry = LocateRegistry.getRegistry("localhost",1313);
		
		// register the instance of Calculator in the Naming Service
		registry.bind("Calculator", calculator);
	}

}
