import DistributionLayer.NodeHandlerFactory;
import DistributionLayer.Publisher;


public class Talker {

	private static String host = "localhost";
	private static int portNodeMaster = 5555;
	
	public static void main(String[] args) throws Throwable {
		
		Publisher publisher = (Publisher) NodeHandlerFactory.getNodeHandler("talker", host, portNodeMaster);
		publisher.advertise("measures/temperature");
		publisher.advertise("measures/pressure");
		publisher.advertise("measures/speed");
		publisher.advertise("measures/levelBattery");
		
		for(int i = 0; i < 10; i++){
			publisher.publish("measures/temperature",Integer.toString(i));
			for(int j = 10; j > 0; j--){
				publisher.publish("measures/levelBattery",Integer.toString(j));
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
		}	
	}
}
