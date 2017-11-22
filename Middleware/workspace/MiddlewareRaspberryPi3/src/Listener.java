import DistributionLayer.CallBack;
import DistributionLayer.NodeHandlerFactory;
import DistributionLayer.Subscriber;

public class Listener {

	private static String host = "localhost";
	private static int portNodeMaster = 5555;
	
	public static void main(String[] args) throws Throwable {
		
		Subscriber subscriber = (Subscriber) NodeHandlerFactory.getNodeHandler("listener", host, portNodeMaster);

		subscriber.subscribe("measures/levelBattery");
		subscriber.subscribe("measures/temperature");
		
		CallBack callBack = new CallBackImpl();
		subscriber.receive(callBack);		
	}
}
