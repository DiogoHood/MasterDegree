package middlewareRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImplRMI extends UnicastRemoteObject implements ICalculatorRMI {

	protected CalculatorImplRMI() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	public float add(float a, float b) throws RemoteException{
		return a + b;
	}
	
	public float sub(float a, float b) throws RemoteException{
		return a - b;
	}
	
	public float div(float a, float b) throws RemoteException{
		return a / b;
	}
	
	public float mul(float a, float b) throws RemoteException{
		return a * b;
	}
}
