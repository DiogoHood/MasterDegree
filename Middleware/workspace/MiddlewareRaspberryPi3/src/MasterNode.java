import DistributionLayer.EventService;

public class MasterNode {

	public static void main(String[] args) throws Throwable {
		
		EventService eventService = new EventService(Integer.parseInt(args[0]));
		eventService.start();
	}
}
