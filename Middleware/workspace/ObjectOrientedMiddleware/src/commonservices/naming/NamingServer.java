package commonservices.naming;

import java.io.IOException;

public class NamingServer {

	public static void main(String[] args) throws IOException, Throwable {

		System.out.println("NamingServer@main@ Starting the NamingServer...");
		
		// obtain instance of Naming Service
		NamingProxy namingProxy = new NamingProxy("localhost", 1313);
		
		// obtain instance of Invoker
		NamingInvoker invoker = new NamingInvoker();
		
		invoker.invoke(namingProxy);
	}
}
