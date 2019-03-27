package com.mima.services;

import org.springframework.stereotype.Service;

import com.mima.utils.MQTTClient;

@Service
public class MQTTService {
	
	private MQTTClient mqttClient;
	
	public boolean connectToMQTTClient(String brokenURL, String topicID, int subQoS) {
		mqttClient = new MQTTClient(brokenURL, topicID, subQoS);
		return mqttClient.connect();
	}
	
	public boolean disconnectToMQTTClient() {
		return mqttClient.disconnect();
	}
	
	public boolean publishMessage(String message) {
		return mqttClient.pubblish(message);
	}
	
	public boolean subscribeTopic() {
		return mqttClient.subscribe();
	}
	
	public String getMessage() {
		return mqttClient.getMessage();
	}

}
