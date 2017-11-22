package commonservices.naming;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.omg.CORBA.portable.UnknownException;

import ClientServerFiles.CalculatorProxy;


public class NamingImpl extends NamingProxy implements INaming {
	
	public NamingImpl(String host, int port) {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bind(String serviceName, CalculatorProxy calculatorProxy) throws UnknownException, IOException, Throwable{
		NamingRecord namingRecord = new NamingRecord();
		namingRecord.setServiceName(serviceName);
		namingRecord.setClientProxy(calculatorProxy);

		getNamingRepository().getNamingRecord().add(namingRecord);
	}
	
	@Override
	public CalculatorProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable{
		
		for(int i = 0; i < getNamingRepository().getNamingRecord().size();i++){
			if(getNamingRepository().getNamingRecord().get(i).getServiceName().equals(serviceName)){
				return getNamingRepository().getNamingRecord().get(i).getClientProxy();
			}
		}
		
		return null;
		
	}
	
	@Override
	public ArrayList<String> list() throws UnknownHostException, IOException, Throwable{
		
		ArrayList<String> listNamingRecord = new ArrayList<String>();
		
		for(int i = 0; i < getNamingRepository().getNamingRecord().size();i++){
			listNamingRecord.add(getNamingRepository().getNamingRecord().get(i).getServiceName());
		}
		return listNamingRecord;		
	}

}
