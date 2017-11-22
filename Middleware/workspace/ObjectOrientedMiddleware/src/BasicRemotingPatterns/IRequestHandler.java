package BasicRemotingPatterns;

import java.io.IOException;

public interface IRequestHandler {
	
	public byte [] receive() throws IOException, InterruptedException;
	public void send(byte [] msg) throws IOException, InterruptedException;

}
