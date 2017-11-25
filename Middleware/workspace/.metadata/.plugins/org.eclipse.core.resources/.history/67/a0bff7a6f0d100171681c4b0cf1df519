package DistributionLayer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueManager {
	
	private static Map <String,Queue<String>> messageQueue = new ConcurrentHashMap<>();
	
	public static void create(String topic){
		
		if(messageQueue.get(topic) == null){
			Queue<String> queue = new ConcurrentLinkedQueue<>();
			messageQueue.put(topic,queue);
			System.out.println("QueueManager@create@ Queue of the topic " + "'" + topic + "'" + " was created.");
		}else{
			System.out.println("QueueManager@create@ Queue of the topic " + "'" + topic + "'" + " already exist.");
		}
	}	
	
	public static void insert(String topic, String message){
		
		if(messageQueue.get(topic) != null){
			Queue<String> queue = messageQueue.get(topic);
			queue.add(message);
			messageQueue.put(topic,queue);
			System.out.println("QueueManager@insert@New message arrived on the topic " + "'" + topic + "'");			
		}
	}
	
	public static String remove(String topic){
		
		if(messageQueue.get(topic) != null){
			
			Queue<String> queue = messageQueue.get(topic);
			if(!queue.isEmpty()){
				return queue.remove();
			}
			else{
//				System.out.println("QueueManager@remove@ There isn't message in the topic " + "'" + topic + "'");
				return null;
			}
		}
		else{
//			System.out.println("QueueManager@remove@ Topic invalid!");
			return null;
		}
	}
	
	public static ArrayList<String> listTopics(){
		
		ArrayList<String> list = new ArrayList<>();

		for (Entry<String, Queue<String>> entry : messageQueue.entrySet()) {
			list.add(entry.getKey());
		}
		
		return list;
	}
	
	public static int getSizeQueue(String topic){
		
		return messageQueue.get(topic).size();
	}
}
