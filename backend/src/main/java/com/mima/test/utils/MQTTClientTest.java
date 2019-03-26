package com.mima.test.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mima.utils.MQTTClient;

public class MQTTClientTest {
	
	private MQTTClient mqttClient;
	
	@Before
	public void setUp() throws Exception {
		mqttClient = new MQTTClient("tcp://test.mosquitto.org:1883", "mima", 0);
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testConnect() {
		boolean connect = mqttClient.connect();
		mqttClient.disconnect();
		Assert.assertTrue(connect);
	}
	
	@Test
	public void testDisconnect() {
		mqttClient.connect();
		boolean disconnect = mqttClient.disconnect();
		Assert.assertTrue(disconnect);
	}
	
	@Test
	public void testPubblish() {
		mqttClient.connect();
		boolean delivered = mqttClient.pubblish("test");
		mqttClient.disconnect();
		Assert.assertTrue(delivered);
	}
	
	@Test
	public void testSubscribe() {
		mqttClient.connect();
		boolean subscribe = mqttClient.subscribe();
		mqttClient.disconnect();
		Assert.assertTrue(subscribe);
	}

}
