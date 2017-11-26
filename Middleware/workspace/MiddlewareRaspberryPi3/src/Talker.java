import DistributionLayer.NodeHandlerFactory;
import DistributionLayer.Publisher;

public class Talker {

	public static void main(String[] args) throws Throwable {
		
		Publisher publisher = (Publisher) NodeHandlerFactory.getNodeHandler("talker", args[0], Integer.parseInt(args[1]));
		
		publisher.advertise("measures/temperature");
		
		for(int i = 0; i < 500; i++){
			publisher.publish("measures/temperature",Integer.toString(i));
			Thread.sleep(100);
		}	
	}
}