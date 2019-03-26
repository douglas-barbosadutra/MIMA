package com.mima.utils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class MQTTClient implements MqttCallback {
	
	private String brokenURL;
	private String topicID;
	private int subQoS = 0;
	
	private MqttClient mqttClient;
	private MqttConnectOptions connOpt;
	private MqttTopic topic;
	
	public MQTTClient(String brokenURL, String topicID, int subQoS) {
		this.brokenURL = brokenURL;
		this.topicID = topicID;
		this.subQoS = subQoS;
	}

	public void connectionLost(Throwable arg0) {
		System.out.println("Connection Lost");

	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println("Delivery Complete");
	}

	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		System.out.println("-------------------------------------------------");
		System.out.println("| Topic:" + arg0);
		System.out.println("| Message: " + new String(arg1.getPayload()));
		System.out.println("-------------------------------------------------");
	}
	
	public boolean connect() {
		connOpt = new MqttConnectOptions();
		connOpt.setCleanSession(true);
		connOpt.setKeepAliveInterval(30);
		
		try {
			mqttClient = new MqttClient(brokenURL, MqttClient.generateClientId());
			mqttClient.setCallback(MQTTClient.this);
			mqttClient.connect(connOpt);
		} catch (MqttException e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println("Connesso con " + brokenURL);
		topic = mqttClient.getTopic(topicID);
		return true;
	}
	
	public boolean subscribe() {
		try {
			mqttClient.subscribe(topic.getName(), subQoS);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println("In ascolto sul topic " + topicID);
		return true;
	}
	
	public boolean pubblish(String message) {
		MqttMessage msg = new MqttMessage(message.getBytes());
		msg.setQos(subQoS);
		msg.setRetained(false);
		
		MqttDeliveryToken token = null;
	      
		try {
	       token = topic.publish(msg);
	       token.waitForCompletion();
	       Thread.sleep(100);
	    } catch (Exception e) {
	       e.printStackTrace();
	       return false;
	    }
		
		System.out.println("Messaggio pubblicato \""+ message +"\" su \"" + topicID + "\" qos " + subQoS);
		return true;
	}
		
	public boolean disconnect() {
		try {
			mqttClient.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		System.out.println("Disconnesso con " + brokenURL);
		return true;
	}
	
}