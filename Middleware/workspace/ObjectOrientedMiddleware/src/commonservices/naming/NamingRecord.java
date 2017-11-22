package commonservices.naming;

import ClientServerFiles.CalculatorProxy;

public class NamingRecord {
	
	private String serviceName;
	private CalculatorProxy calculatorProxy;
	
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}
	
	public String getServiceName(){
		return this.serviceName;
	}
	
	public void setClientProxy(CalculatorProxy clientProxy){
		this.calculatorProxy = clientProxy;
	}
	
	public CalculatorProxy getClientProxy(){
		return this.calculatorProxy;
	}

}
