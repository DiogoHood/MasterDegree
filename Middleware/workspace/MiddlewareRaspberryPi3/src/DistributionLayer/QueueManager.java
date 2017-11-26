package DistributionLayer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import Message.Message;
import Message.MessageBody;
import Message.MessageHeader;

public class QueueManager {
	
	private static Map <String,Queue<Message>> messageQueue = new ConcurrentHashMap<>();
	
	public static void create(String topic){
		
		if(messageQueue.get(topic) == null){
			Queue<Message> queue = new ConcurrentLinkedQueue<>();
			messageQueue.put(topic,queue);
			System.out.println("QueueManager@create@ Queue of the topic " + "'" + topic + "'" + " was created.");
		}else{
			System.out.println("QueueManager@create@ Queue of the topic " + "'" + topic + "'" + " already exist.");
		}
	}	
	
	public static void insert(String topic, String message, long time){
		
		if(messageQueue.get(topic) != null){ 
			Message msg = new Message(new MessageHeader(null,0,null,topic,time), new MessageBody(message));
			Queue<Message> queue = messageQueue.get(topic);
			queue.add(msg);
			messageQueue.put(topic,queue);
			System.out.println("QueueManager@insert@New message arrived on the topic " + "'" + topic + "'");			
		}
	}
	
	public static Message remove(String topic){
		
		if(messageQueue.get(topic) != null){
			
			Queue<Message> queue = messageQueue.get(topic);
			if(!queue.isEmpty()){
				return queue.remove();
			}
			else{
				System.out.println("QueueManager@remove@ There isn't message in the topic " + "'" + topic + "'");
				return null;
			}
		}
		else{
			System.out.println("QueueManager@remove@ Topic invalid!");
			return null;
		}
	}
	
	public static ArrayList<String> listTopics(){
		
		ArrayList<String> list = new ArrayList<>();

		for (Entry<String, Queue<Message>> entry : messageQueue.entrySet()) {
			list.add(entry.getKey());
		}
		
		return list;
	}
	
	public static int getSizeQueue(String topic){
		
		return messageQueue.get(topic).size();
	}
}
