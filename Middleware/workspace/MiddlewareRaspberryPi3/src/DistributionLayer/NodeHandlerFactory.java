package DistributionLayer;

public class NodeHandlerFactory {
	
	public static INodeHandler getNodeHandler(String type, String host, int portNodeMaster){

		if(type.equals("talker")){
			return new Publisher(host,portNodeMaster);
		}else if(type.equals("listener")){
			return new Subscriber(host, portNodeMaster);
		}

		return null;
	}
}
