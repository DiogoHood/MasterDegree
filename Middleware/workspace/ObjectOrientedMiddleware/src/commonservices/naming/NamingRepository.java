package commonservices.naming;

import java.util.ArrayList;

public class NamingRepository {
	
	private ArrayList<NamingRecord> namingRecord = new ArrayList<NamingRecord>();
	
	public NamingRepository(){
		
	}
	
	public void setNamingRecord(ArrayList<NamingRecord> namingRecord){
		this.namingRecord = namingRecord;
	}
	
	public ArrayList<NamingRecord> getNamingRecord(){
		return this.namingRecord;
	}

}
