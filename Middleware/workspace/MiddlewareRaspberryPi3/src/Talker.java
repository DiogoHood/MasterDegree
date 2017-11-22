import DistributionLayer.NodeHandlerFactory;
import DistributionLayer.Publisher;


public class Talker {

	private static String host = "localhost";
	private static int portNodeMaster = 5555;
	
	public static void main(String[] args) throws Throwable {
		
		Publisher publisher = (Publisher) NodeHandlerFactory.getNodeHandler("talker", host, portNodeMaster);
		publisher.advertise("measures/temperature", null);
		publisher.advertise("measures/pressure", null);
		publisher.advertise("measures/speed", null);
		publisher.advertise("measures/levelBattery", null);
		
		for(int i = 0; i < 10; i++){
			publisher.publish("measures/temperature",Integer.toString(i), null);
			for(int j = 10; j > 0; j--){
				publisher.publish("measures/levelBattery",Integer.toString(j), null);
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
		}	
	}
}
