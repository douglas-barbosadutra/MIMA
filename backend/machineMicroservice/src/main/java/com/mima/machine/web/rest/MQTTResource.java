package com.mima.machine.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mima.machine.service.dto.MqttDTO;
import com.mima.machine.service.impl.MQTTServiceImpl;
import com.netflix.ribbon.proxy.annotation.Http;

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
	
	@PostMapping("/publishMessage")
    public ResponseEntity<Boolean> publishMessage(@RequestBody String message) {
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
	
	//test comunicazione tra microservizi
	@GetMapping("/testComunication")
    public ResponseEntity<String> testComunication() {
			
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTU1NDg4NjM5MX0.hh1C_2ONuDUi_JgSAa5ygn_0-ZtNRPtPKlwvr-O23hQxfthzceCc7dQB3iE6eBJOMOC3GQZki_V8X-vLWjmf-A");
		
		HttpEntity<?> request1 = new HttpEntity(String.class, headers);
		ResponseEntity<String> responseEntity = new RestTemplate().exchange("http://localhost:8080/machineMicroservice/api/machines", HttpMethod.GET, request1, String.class);
		return ResponseEntity.ok().body(responseEntity.getBody()); 

    }
	
}
