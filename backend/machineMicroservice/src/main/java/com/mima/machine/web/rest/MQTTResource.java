package com.mima.machine.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mima.machine.service.dto.MqttDTO;
import com.mima.machine.service.impl.MQTTServiceImpl;

/**
 * REST controller for managing MQTT.
 */
@RestController
@RequestMapping("/api")
public class MQTTResource {
	
	@Autowired
	private MQTTServiceImpl mqttService;

	@PostMapping("/connectMQTT")
    public ResponseEntity<Boolean> connectMQTT(@RequestBody MqttDTO mqttDTO) {
		boolean result = mqttService.connectToMQTTClient(mqttDTO.getBrokenURL(), mqttDTO.getTopic(), mqttDTO.getQos());
		return ResponseEntity.ok().body(result);
    }
	
	@PostMapping("/disconnectMQTT")
    public ResponseEntity<Boolean> disconnectMQTT() {
		boolean result = mqttService.disconnectToMQTTClient();
		return ResponseEntity.ok().body(result);
    }
	
	//non testato
	@PostMapping("/publishMessage")
    public ResponseEntity<Boolean> publishMessage(@RequestParam(value="message") String message) {
		boolean result = mqttService.publishMessage(message);
		return ResponseEntity.ok().body(result);
    }
	
	//non testato
	@PostMapping("/subscribeTopic")
    public ResponseEntity<Boolean> subscribeTopic() {
		boolean result = mqttService.subscribeTopic();
		return ResponseEntity.ok().body(result);
    }
	
	//non testato
	@PostMapping("/getMessage")
    public ResponseEntity<String> getMessage() {
		String result = mqttService.getMessage();
		return ResponseEntity.ok().body(result);
    }
	
}
