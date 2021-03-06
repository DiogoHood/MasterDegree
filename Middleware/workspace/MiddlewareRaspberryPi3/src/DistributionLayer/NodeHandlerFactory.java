package DistributionLayer;

import java.io.IOException;
import java.net.ServerSocket;

public class NodeHandlerFactory {
	
	public static INodeHandler getNodeHandler(String type, String host, int portNodeMaster){

		if(type.equals("talker")){
			return new Publisher(host,portNodeMaster);
		}else if(type.equals("listener")){
			Subscriber subscriber = new Subscriber(host, portNodeMaster);
			subscriber.setPortListener(findFreePort());
			return subscriber;
		}

		return null;
	}
	
	private static int findFreePort() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(0);
			socket.setReuseAddress(true);
			int port = socket.getLocalPort();
			try {
				socket.close();
			} catch (IOException e) {
				// Ignore IOException on close()
			}
			return port;
		} catch (IOException e) { 
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		throw new IllegalStateException("Could not find a free TCP/IP port to start embedded Jetty HTTP Server on");
	}
}
