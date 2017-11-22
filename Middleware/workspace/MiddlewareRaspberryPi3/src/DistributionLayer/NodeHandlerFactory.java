package DistributionLayer;

public class NodeHandlerFactory {
	
	public static INodeHandler getNodeHandler(String type, String host, int portNodeMaster){

		if(type.equals("talker")){
			return new Publisher(host,portNodeMaster);
		}else if(type.equals("listener")){
			Subscriber subscriber = new Subscriber(host, portNodeMaster);
			subscriber.setPortListener(11111);
			return subscriber;
		}

		return null;
	}
}
