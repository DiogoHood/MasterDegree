package middlewareRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculatorRMI extends Remote {
	
	public float add(float a, float b) throws RemoteException;
	
	public float sub(float a, float b) throws RemoteException;
	
	public float div(float a, float b) throws RemoteException;
	
	public float mul(float a, float b) throws RemoteException;

}
