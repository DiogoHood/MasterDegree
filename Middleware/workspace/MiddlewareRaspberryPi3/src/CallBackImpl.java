import java.util.concurrent.TimeUnit;

import DistributionLayer.CallBack;

public class CallBackImpl implements CallBack {
	
    public void methodToCallBack(String mensagem, String topic, long time) {
    	System.out.println("Subscriber@receive@ Message received from topic '" + topic +"': " + mensagem);
//    	System.out.println(time);
    }
}
