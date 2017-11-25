import DistributionLayer.CallBack;
import DistributionLayer.NodeHandlerFactory;
import DistributionLayer.Subscriber;

public class Listener {

	public static void main(String[] args) throws Throwable {
		
		Subscriber subscriber = (Subscriber) NodeHandlerFactory.getNodeHandler("listener", args[0], Integer.parseInt(args[1]));

		subscriber.subscribe("measures/temperature");
		
		CallBack callBack = new CallBackImpl();
		subscriber.receive(callBack);		
	}
}
