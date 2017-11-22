package BasicRemotingPatterns;

import java.util.ArrayList;

import ClientServerFiles.CalculatorProxy;

public class ObjectsPull {
	
	private ArrayList <CalculatorProxy> pull =  new ArrayList<CalculatorProxy>();
	
	public void addObject(CalculatorProxy object){
		pull.add(object);
	}
	
	public CalculatorProxy getObject(int objectID){
		
		for(int i = 0; i < pull.size(); i++){
			
			if(pull.get(i).getAOR().getObjectID() == objectID){
				return pull.get(i);
			}
		}
		
		return null;		
	}
}
