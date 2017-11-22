import DistributionLayer.CallBack;
import DistributionLayer.NodeHandlerFactory;
import DistributionLayer.Subscriber;

public class Listener {

	private static String host = "localhost";
	private static int portNodeMaster = 5555;
	private static int portListener = 11111;
	
	public static void main(String[] args) throws Throwable {
		
		Subscriber subscriber = (Subscriber) NodeHandlerFactory.getNodeHandler("listener", host, portNodeMaster);

		subscriber.subscribe("measures/levelBattery",null,portListener);
		subscriber.subscribe("measures/temperature",null,portListener);
		CallBack callBack = new CallBackImpl();
		subscriber.receive(callBack,portListener);		
	}
}
