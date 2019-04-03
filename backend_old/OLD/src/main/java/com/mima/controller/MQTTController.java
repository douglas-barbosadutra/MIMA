package com.mima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mima.services.MQTTService;


@CrossOrigin(value="*")
@RestController
@RequestMapping("/MQTT")
public class MQTTController {
	
	@Autowired
	private MQTTService mqttService;
	
	@PostMapping("/connectMQTT")
	public ResponseEntity<Boolean> connectMQTT(@RequestParam(value="brokenURL") String brokenURL, @RequestParam(value="topicID") String topicID, @RequestParam(value="subQoS") int subQoS) {
		return ResponseEntity.status(HttpStatus.OK).body(mqttService.connectToMQTTClient(brokenURL,topicID,subQoS));
	}
	
	@PostMapping("/disconnectMQTT")
	public ResponseEntity<Boolean> disconnectMQTT() {
		return ResponseEntity.status(HttpStatus.OK).body(mqttService.disconnectToMQTTClient());
	}
	
	@PostMapping("/publishMessage")
	public ResponseEntity<Boolean> publishMessage(@RequestParam(value="message") String message) {
		return ResponseEntity.status(HttpStatus.OK).body(mqttService.publishMessage(message));
	}
	
	@PostMapping("/subscribeTopic")
	public ResponseEntity<Boolean> subscribeTopic() {
		return ResponseEntity.status(HttpStatus.OK).body(mqttService.subscribeTopic());
	}
	
	@PostMapping("/getMessage")
	public ResponseEntity<String> getMessage() {
		return ResponseEntity.status(HttpStatus.OK).body(mqttService.getMessage());
	}

}
