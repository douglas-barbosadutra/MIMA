package com.mima.machine.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

import com.mima.machine.security.SecurityUtils;
import com.mima.machine.service.dto.MachineDTO;
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
    public String getMessage() throws JSONException {
		String result = mqttService.getMessage();
		return new JSONObject().put("message", result).toString();
    }
	
	//test comunicazione tra microservizi
	@GetMapping("/testComunication")
    public ResponseEntity<String> testComunication() {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", "Bearer " + SecurityUtils.getCurrentUserJWT().get());
		
		MachineDTO machineDTO = new MachineDTO();
		machineDTO.setId(null);
		machineDTO.setIdUser(4);
		machineDTO.setName("nome");
		machineDTO.setModel("modello");
		
		HttpEntity<MachineDTO> request1 = new HttpEntity<MachineDTO>(machineDTO, headers);
		ResponseEntity<MachineDTO> responseEntity = new RestTemplate().exchange("http://localhost:8080/machineMicroservice/api/machines", HttpMethod.POST, request1, MachineDTO.class);
		
		return ResponseEntity.ok().body(responseEntity.getBody().toString()); 
    }
	
}
